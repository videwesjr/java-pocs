package com.java11.epsilon;

/**
 * JEP 318: Epsilon, a No-Op Garbage Collector.
 *
 * Epsilon allocates memory but never reclaims it, which is useful for
 * performance testing, very short-lived jobs, or memory-pressure testing
 * where you want to measure raw allocation cost without GC interference.
 *
 * Run with:
 *   java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -Xmx256m \
 *        com.java11.epsilon.EpsilonGcDemo
 *
 * With the default collector this loop runs to completion because garbage
 * is reclaimed; with Epsilon GC it will eventually throw OutOfMemoryError
 * once the heap fills up, since nothing is ever collected.
 */
public class EpsilonGcDemo {

    public static void main(String[] args) {
        long allocated = 0;
        try {
            for (int i = 0; i < 1_000_000; i++) {
                byte[] chunk = new byte[1024]; // 1 KB
                allocated += chunk.length;
                if (i % 50_000 == 0) {
                    System.out.println("Allocated ~" + (allocated / 1024 / 1024) + " MB");
                }
            }
            System.out.println("Finished without running out of memory.");
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError after ~" + (allocated / 1024 / 1024) + " MB (expected with Epsilon GC)");
        }
    }
}
