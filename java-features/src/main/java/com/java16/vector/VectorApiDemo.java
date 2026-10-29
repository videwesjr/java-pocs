package com.java16.vector;

import java.util.Arrays;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

/**
 * JEP 338: Vector API (incubator in Java 16).
 *
 * An API for expressing computations that the JIT reliably compiles to SIMD
 * instructions (AVX on x64, NEON on AArch64), instead of hoping the
 * autovectorizer recognises a scalar loop. It is still incubating -- JDK 26
 * ships a further round -- because it waits on Project Valhalla, so the
 * module must be requested explicitly and the API may change in any release.
 *
 * Compile and run with:
 *   javac --add-modules jdk.incubator.vector ...
 *   java  --add-modules jdk.incubator.vector com.java16.vector.VectorApiDemo
 *
 * The build.gradle of this project passes --add-modules at compile time.
 */
public class VectorApiDemo {

    /**
     * SPECIES_PREFERRED picks the widest shape the current CPU supports, so
     * the lane count is a property of the machine, not of the source.
     */
    private static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

    public static void main(String[] args) {
        System.out.println("species: " + SPECIES + " (" + SPECIES.length() + " lanes, "
                + SPECIES.vectorBitSize() + " bits)");

        float[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        float[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        float[] scalar = hypotScalar(a, b);
        float[] scalarTail = hypotVectorWithScalarTail(a, b);
        float[] maskedTail = hypotVectorWithMaskedTail(a, b);

        System.out.println();
        System.out.println("scalar:              " + Arrays.toString(scalar));
        System.out.println("vector, scalar tail: " + Arrays.toString(scalarTail));
        System.out.println("vector, masked tail: " + Arrays.toString(maskedTail));
        System.out.println("all three agree:     "
                + (Arrays.equals(scalar, scalarTail) && Arrays.equals(scalar, maskedTail)));

        reductionAndComparison(a);
    }

    private static float[] hypotScalar(float[] a, float[] b) {
        float[] result = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = (float) Math.sqrt(a[i] * a[i] + b[i] * b[i]);
        }
        return result;
    }

    /**
     * The classic shape: step through the array a vector at a time up to
     * loopBound, then finish the remainder with the scalar loop.
     */
    private static float[] hypotVectorWithScalarTail(float[] a, float[] b) {
        float[] result = new float[a.length];
        int upperBound = SPECIES.loopBound(a.length);
        int i = 0;

        for (; i < upperBound; i += SPECIES.length()) {
            FloatVector va = FloatVector.fromArray(SPECIES, a, i);
            FloatVector vb = FloatVector.fromArray(SPECIES, b, i);
            va.mul(va).add(vb.mul(vb)).sqrt().intoArray(result, i);
        }
        for (; i < a.length; i++) {
            result[i] = (float) Math.sqrt(a[i] * a[i] + b[i] * b[i]);
        }
        return result;
    }

    /**
     * The alternative: keep a single loop and mask off the lanes past the end
     * of the array, so the remainder needs no scalar code of its own.
     */
    private static float[] hypotVectorWithMaskedTail(float[] a, float[] b) {
        float[] result = new float[a.length];

        for (int i = 0; i < a.length; i += SPECIES.length()) {
            VectorMask<Float> mask = SPECIES.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(SPECIES, a, i, mask);
            FloatVector vb = FloatVector.fromArray(SPECIES, b, i, mask);
            va.mul(va).add(vb.mul(vb)).sqrt().intoArray(result, i, mask);
        }
        return result;
    }

    /**
     * Lanewise operations also cover cross-lane reductions, and comparisons
     * that produce a mask rather than a vector.
     */
    private static void reductionAndComparison(float[] a) {
        FloatVector first = FloatVector.fromArray(SPECIES, a, 0, SPECIES.indexInRange(0, a.length));

        System.out.println();
        System.out.println("first vector:   " + first);
        System.out.println("sum of lanes:   " + first.reduceLanes(VectorOperators.ADD));
        System.out.println("max of lanes:   " + first.reduceLanes(VectorOperators.MAX));

        VectorMask<Float> aboveThree = first.compare(VectorOperators.GT, 3.0f);
        System.out.println("lanes > 3:      " + aboveThree + " (" + aboveThree.trueCount() + " lanes)");
        System.out.println("doubled if > 3: " + first.mul(2.0f, aboveThree));
    }
}
