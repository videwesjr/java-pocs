package com.behavioral.state;

public final class StateDemo {

    private StateDemo() {
    }

    public static void run() {
        System.out.println("=== State ===");

        Document document = new Document();
        System.out.println("Current state: " + document.currentState());

        document.publish();
        document.publish();

        document.setAdmin(true);
        document.publish();

        document.publish();
    }
}
