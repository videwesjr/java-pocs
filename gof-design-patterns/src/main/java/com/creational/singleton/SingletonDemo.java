package com.creational.singleton;

public final class SingletonDemo {

    private SingletonDemo() {
    }

    public static void run() {
        System.out.println("=== Singleton ===");

        DatabaseConnection first = DatabaseConnection.getInstance();
        DatabaseConnection second = DatabaseConnection.getInstance();

        first.query("SELECT 1");
        second.query("SELECT 2");

        System.out.println("Same instance? " + (first == second));
    }
}
