package com.java17.switchpattern;

import java.util.List;

/**
 * JEP 406: Pattern Matching for switch (preview in Java 17).
 *
 * case labels may hold a type pattern, so a switch dispatches on the shape
 * of its selector rather than only on a constant value. That turns the
 * instanceof chains of the earlier demos into a single expression the
 * compiler can check for exhaustiveness.
 *
 * The feature previewed four times -- JEP 406 (17), JEP 420 (18),
 * JEP 427 (19), JEP 433 (20) -- before JEP 441 standardized it in Java 21,
 * and the syntax moved on the way. This file compiles on a modern JDK, so it
 * uses the final spelling; where JEP 406 differed:
 *   - guards were written `case Type t && condition ->`; the `when` clause
 *     used below arrived with the third preview (JEP 427), which also
 *     dropped parenthesized patterns such as `case (Integer i) ->`;
 *   - dominance checking between case labels was tightened by JEP 420;
 *   - MatchException, for a switch whose patterns no longer cover the
 *     selector at run time, came with JEP 433.
 *
 * Null was part of JEP 406 from the start: a switch on a reference type
 * throws NullPointerException unless the block says `case null`.
 */
public class SwitchPatternMatchingDemo {

    sealed interface Shape permits Circle, Square, Rectangle {
    }

    record Circle(double radius) implements Shape {
    }

    record Square(double side) implements Shape {
    }

    record Rectangle(double width, double height) implements Shape {
    }

    public static void main(String[] args) {
        typePatterns();
        guardedPatterns();
        nullHandling();
        exhaustiveOverSealedType();
    }

    /**
     * The motivating example: one switch replacing a chain of instanceof
     * tests, each label binding its own variable.
     */
    private static void typePatterns() {
        for (Object value : List.of(42, 7L, 2.5, "duke", 'x', List.of(1, 2), new int[]{1, 2, 3})) {
            System.out.printf("  %-14s -> %s%n", describe(value), format(value));
        }
    }

    private static String format(Object value) {
        return switch (value) {
            case Integer i -> "int %d (hex %x)".formatted(i, i);
            case Long l -> "long %d".formatted(l);
            case Double d -> "double %.2f".formatted(d);
            case String s -> "string of length %d".formatted(s.length());
            case int[] array -> "int[] of %d elements".formatted(array.length);
            case List<?> list -> "list of %d elements".formatted(list.size());
            // No pattern covers Object, so a default is still required.
            default -> "unhandled " + value.getClass().getSimpleName();
        };
    }

    /**
     * A guard narrows a label further. Order matters: the more specific
     * label has to come first, because a switch takes the first match.
     */
    private static void guardedPatterns() {
        System.out.println();
        for (Object value : List.of(5, 150, "hi", "a longer string", 0.5)) {
            System.out.printf("  %-18s -> %s%n", describe(value), classify(value));
        }
    }

    private static String classify(Object value) {
        return switch (value) {
            case Integer i when i > 100 -> "large number";
            case Integer i -> "small number";
            case String s when s.length() > 5 -> "long text";
            case String s -> "short text";
            default -> "something else";
        };
    }

    /**
     * A switch on a reference type has always thrown on null before even
     * looking at the labels. A pattern switch can take null over explicitly,
     * either on its own or folded into the default label.
     */
    private static void nullHandling() {
        System.out.println();
        System.out.println("  without case null: " + lengthOrThrow("abc"));
        try {
            lengthOrThrow(null);
        } catch (NullPointerException e) {
            System.out.println("  without case null: NullPointerException on null, as before");
        }

        System.out.println("  with case null:    " + lengthOrZero(null));
        System.out.println("  with case null:    " + lengthOrZero("abcd"));
    }

    private static int lengthOrThrow(String text) {
        return switch (text) {
            case "abc" -> 3;
            default -> text.length();
        };
    }

    private static int lengthOrZero(String text) {
        return switch (text) {
            case null -> 0;
            case "" -> 0;
            default -> text.length();
            // `case null, default ->` would merge the two into one label.
        };
    }

    /**
     * The payoff of sealing a hierarchy: the switch covers every permitted
     * subtype, so it needs no default and no unreachable throw. Adding a
     * fourth Shape would turn this switch into a compile error, which is
     * exactly the reminder you want.
     *
     * A label that dominates a later one is rejected -- putting
     * `case Shape s` first here would be an error, not a silent catch-all.
     */
    private static void exhaustiveOverSealedType() {
        System.out.println();
        for (Shape shape : List.of(new Circle(1), new Square(2), new Rectangle(2, 3))) {
            System.out.printf("  %-24s area=%6.3f%n", shape, area(shape));
        }
    }

    private static double area(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Square s -> s.side() * s.side();
            case Rectangle r -> r.width() * r.height();
        };
    }

    private static String describe(Object value) {
        return value instanceof int[] array ? "int[]" + java.util.Arrays.toString(array) : String.valueOf(value);
    }
}
