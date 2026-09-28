package com.java12.switchexpr;

/**
 * JEP 325 / JEP 354: Switch Expressions (preview in Java 12 and 13, standard
 * since Java 14).
 *
 * Arrow-case switch that yields a value directly, with no fall-through
 * between branches. The syntax previewed here is plain standard Java on this
 * JDK, since switch expressions have been stable since Java 14.
 */
public class SwitchExpressionDemo {

    public static void main(String[] args) {
        for (int day = 1; day <= 7; day++) {
            System.out.println("Day " + day + " -> " + classify(day));
        }
    }

    private static String classify(int day) {
        return switch (day) {
            case 1, 2, 3, 4, 5 -> "Weekday";
            case 6, 7 -> "Weekend";
            default -> throw new IllegalArgumentException("Invalid day: " + day);
        };
    }
}
