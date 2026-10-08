package com.java14.switchexpr;

/**
 * JEP 361: Switch Expressions (standard in Java 14).
 *
 * After two previews, switch expressions became a permanent feature. A
 * switch expression over an enum that covers every constant is exhaustive,
 * so no default branch is needed; adding a new constant turns a missing
 * case into a compile error instead of a silent bug.
 */
public class StandardSwitchDemo {

    enum Size { SMALL, MEDIUM, LARGE, EXTRA_LARGE }

    public static void main(String[] args) {
        for (Size size : Size.values()) {
            System.out.println(size + " -> " + priceInCents(size) + " cents");
        }

        // Arrow labels also work in switch statements: no fall-through, no break.
        Size order = Size.LARGE;
        switch (order) {
            case SMALL, MEDIUM -> System.out.println("Fits in a bag");
            case LARGE, EXTRA_LARGE -> System.out.println("Needs a box");
        }
    }

    private static int priceInCents(Size size) {
        return switch (size) {
            case SMALL -> 300;
            case MEDIUM -> 450;
            case LARGE -> 600;
            case EXTRA_LARGE -> {
                int base = priceInCents(Size.LARGE);
                yield base + base / 4;
            }
        };
    }
}
