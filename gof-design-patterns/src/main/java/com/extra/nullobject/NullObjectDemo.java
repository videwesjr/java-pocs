package com.extra.nullobject;

import java.util.Map;

public final class NullObjectDemo {

    private NullObjectDemo() {
    }

    public static void run() {
        System.out.println("=== Null Object ===");

        CustomerRegistry registry = new CustomerRegistry(
                Map.of("john", new RealCustomer("John Doe")));

        Customer john = registry.find("john");
        Customer unknown = registry.find("ghost");

        System.out.println("john -> " + john.getName() + " (isNull=" + john.isNull() + ")");
        System.out.println("ghost -> " + unknown.getName() + " (isNull=" + unknown.isNull() + ")");
    }
}
