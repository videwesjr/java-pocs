package com.java14.helpfulnpe;

/**
 * JEP 358: Helpful NullPointerExceptions.
 *
 * The JVM now describes exactly which expression was null, e.g.
 * 'Cannot invoke "String.length()" because "...address.city" is null'.
 * Opt-in via -XX:+ShowCodeDetailsInExceptionMessages in Java 14, enabled by
 * default since Java 15.
 *
 * Local variable names appear in the message only when the class is
 * compiled with -g; otherwise they show as "<local1>" and so on.
 */
public class HelpfulNullPointerDemo {

    static class Address {
        String city;
    }

    static class Customer {
        Address address;
    }

    public static void main(String[] args) {
        Customer withoutCity = new Customer();
        withoutCity.address = new Address();

        run("null field in a chain", () -> System.out.println(withoutCity.address.city.length()));
        run("null intermediate object", () -> System.out.println(new Customer().address.city));
        run("null array element", () -> {
            int[][] matrix = new int[2][];
            matrix[1][0] = 5;
        });
    }

    private static void run(String label, Runnable action) {
        try {
            action.run();
        } catch (NullPointerException e) {
            System.out.println(label + ": " + e.getMessage());
        }
    }
}
