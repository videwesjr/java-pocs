package com.java16.instanceofpattern;

import java.util.List;
import java.util.Objects;

/**
 * JEP 394: Pattern Matching for instanceof (standard in Java 16).
 *
 * Preview in Java 14 (JEP 305) and Java 15 (JEP 375). Java 16 finalized the
 * type pattern with no syntax changes, so the binding variable needs no
 * --enable-preview. Finalizing it also tightened one rule: an instanceof
 * pattern whose type is no narrower than the expression's static type is now
 * a compile-time error, because the test can never fail.
 *
 *   String s = "x";
 *   if (s instanceof String t) { }   // error: pattern always matches
 *
 * The binding's scope is determined by flow analysis -- it exists exactly
 * where the compiler can prove the test succeeded -- which is what lets a
 * pattern be used in the middle of a condition, after an early return, or in
 * a loop header.
 */
public class StandardInstanceofPatternDemo {

    /** The motivating example from the JEP: equals without a cast. */
    static final class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Point other && x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    /** A box that may wrap another box, to unwrap in a loop header. */
    sealed interface Node permits Box, Leaf {
    }

    record Box(Node inner) implements Node {
    }

    record Leaf(String value) implements Node {
    }

    public static void main(String[] args) {
        equalsWithoutCast();
        flowScoping();
        unwrapInLoopHeader();
    }

    private static void equalsWithoutCast() {
        Point a = new Point(1, 2);

        System.out.println("equal points:   " + a.equals(new Point(1, 2)));
        System.out.println("other type:     " + a.equals("not a point"));
        System.out.println("null:           " + a.equals(null) + " (a pattern never matches null)");
    }

    private static void flowScoping() {
        System.out.println();
        for (Object value : List.of("abcdef", 42, 2.5, List.of(1, 2, 3))) {
            System.out.printf("  %-12s -> %s%n", value, lengthOf(value));
        }
    }

    private static String lengthOf(Object value) {
        // Negating the test puts the binding in scope for everything after
        // the early return, not inside the if.
        if (!(value instanceof CharSequence text)) {
            return value instanceof List<?> list
                    ? "collection of " + list.size()
                    : "no length (" + value.getClass().getSimpleName() + ")";
        }
        return "text of length " + text.length();
    }

    private static void unwrapInLoopHeader() {
        Node nested = new Box(new Box(new Box(new Leaf("centre"))));

        int depth = 0;
        Node current = nested;
        // The binding is live inside the loop body on every iteration where
        // the test passed.
        while (current instanceof Box box) {
            current = box.inner();
            depth++;
        }

        System.out.println();
        System.out.println("unwrapped " + depth + " boxes, found " + current);
    }
}
