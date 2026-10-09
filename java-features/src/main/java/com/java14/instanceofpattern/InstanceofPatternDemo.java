package com.java14.instanceofpattern;

import java.util.List;

/**
 * JEP 305: Pattern Matching for instanceof (preview in Java 14, standard
 * since Java 16).
 *
 * instanceof can bind a variable when the test succeeds, removing the
 * redundant cast. The binding is in scope wherever the compiler can prove
 * the test passed, including the rest of an && condition.
 */
public class InstanceofPatternDemo {

    public static void main(String[] args) {
        List<Object> values = List.of("hello", 42, 3.5, List.of(1, 2, 3), "");

        for (Object value : values) {
            System.out.println("'" + value + "' -> " + describe(value));
        }
    }

    private static String describe(Object obj) {
        // Before Java 14:
        //   if (obj instanceof String) { String s = (String) obj; ... }
        if (obj instanceof String s && !s.isEmpty()) {
            return "non-empty String of length " + s.length();
        }
        if (obj instanceof Integer i) {
            return "Integer, doubled = " + (i * 2);
        }
        if (!(obj instanceof Number n)) {
            return obj instanceof List<?> list ? "List of size " + list.size() : "something else";
        }
        // n is in scope here because the negated test above returned early.
        return "Number, as double = " + n.doubleValue();
    }
}
