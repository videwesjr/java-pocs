package com.java16.foreign;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;

/**
 * JEP 389: Foreign Linker API (incubator in Java 16).
 *
 * The incubating jdk.incubator.foreign module introduced a pure-Java way to
 * call native code: look a symbol up, describe its signature, and get back a
 * MethodHandle -- no JNI glue and no C compiler. It shipped alongside the
 * Foreign-Memory Access API (JEP 393); the two were merged and finalized as
 * the Foreign Function and Memory API in Java 22 (JEP 454), which is the
 * java.lang.foreign package used below.
 *
 * The incubating names of Java 16 map onto the current API as:
 *   CLinker        -> Linker
 *   LibraryLookup  -> SymbolLookup
 *   MemoryAddress  -> MemorySegment
 *   ResourceScope  -> Arena
 *
 * Linker methods are restricted: calling them without native access enabled
 * prints a warning today and will fail in a future release. Run with:
 *   java --enable-native-access=ALL-UNNAMED com.java16.foreign.ForeignLinkerDemo
 */
public class ForeignLinkerDemo {

    private static final Linker LINKER = Linker.nativeLinker();
    private static final SymbolLookup STANDARD_LIBRARY = LINKER.defaultLookup();

    public static void main(String[] args) throws Throwable {
        downcallWithPrimitives();
        downcallWithNativeString();
        upcallFromNativeCode();
    }

    /** int abs(int) -- the simplest possible downcall. */
    private static void downcallWithPrimitives() throws Throwable {
        MethodHandle abs = LINKER.downcallHandle(
                STANDARD_LIBRARY.find("abs").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));

        // invokeExact is strict about types, so the cast is part of the call.
        System.out.println("abs(-42) = " + (int) abs.invokeExact(-42));
    }

    /** size_t strlen(const char *) -- needs off-heap memory for the argument. */
    private static void downcallWithNativeString() throws Throwable {
        MethodHandle strlen = LINKER.downcallHandle(
                STANDARD_LIBRARY.find("strlen").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS));

        // The arena owns the allocation: leaving the block frees it and
        // invalidates every segment it handed out.
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment text = arena.allocateFrom("hello, native world");
            System.out.println("strlen   = " + (long) strlen.invokeExact(text));
        }
    }

    /**
     * void qsort(void *, size_t, size_t, int (*)(const void *, const void *))
     * -- an upcall: native code calls back into Java through a stub.
     */
    private static void upcallFromNativeCode() throws Throwable {
        MethodHandle comparator = MethodHandles.lookup().findStatic(
                ForeignLinkerDemo.class,
                "compareInts",
                MethodType.methodType(int.class, MemorySegment.class, MemorySegment.class));

        // ADDRESS.withTargetLayout says how much memory the pointers passed
        // to the callback may be read through.
        FunctionDescriptor comparatorDescriptor = FunctionDescriptor.of(
                ValueLayout.JAVA_INT,
                ValueLayout.ADDRESS.withTargetLayout(ValueLayout.JAVA_INT),
                ValueLayout.ADDRESS.withTargetLayout(ValueLayout.JAVA_INT));

        MethodHandle qsort = LINKER.downcallHandle(
                STANDARD_LIBRARY.find("qsort").orElseThrow(),
                FunctionDescriptor.ofVoid(
                        ValueLayout.ADDRESS,
                        ValueLayout.JAVA_LONG,
                        ValueLayout.JAVA_LONG,
                        ValueLayout.ADDRESS));

        int[] values = {9, 1, 8, 2, 7, 3};

        try (Arena arena = Arena.ofConfined()) {
            MemorySegment stub = LINKER.upcallStub(comparator, comparatorDescriptor, arena);
            MemorySegment array = arena.allocateFrom(ValueLayout.JAVA_INT, values);

            qsort.invokeExact(array, (long) values.length, ValueLayout.JAVA_INT.byteSize(), stub);

            System.out.println();
            System.out.println("before qsort = " + Arrays.toString(values));
            System.out.println("after qsort  = " + Arrays.toString(array.toArray(ValueLayout.JAVA_INT)));
        }
    }

    /** Invoked by the C library, once per comparison. */
    private static int compareInts(MemorySegment left, MemorySegment right) {
        return Integer.compare(
                left.get(ValueLayout.JAVA_INT, 0),
                right.get(ValueLayout.JAVA_INT, 0));
    }
}
