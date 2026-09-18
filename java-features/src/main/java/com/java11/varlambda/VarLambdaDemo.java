package com.java11.varlambda;

import java.util.List;
import java.util.function.BiFunction;

/**
 * JEP 323: Local-Variable Syntax for Lambda Parameters.
 * Lambda parameters can now use {@code var}, which mainly matters when you
 * need to attach a modifier or annotation (e.g. {@code (@NonNull var a) -> ...})
 * without losing type inference.
 */
public class VarLambdaDemo {

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> sum = (var a, var b) -> a + b;
        System.out.println("3 + 4 = " + sum.apply(3, 4));

        List.of("alpha", "beta", "gamma")
                .forEach((var s) -> System.out.println(s.toUpperCase()));
    }
}
