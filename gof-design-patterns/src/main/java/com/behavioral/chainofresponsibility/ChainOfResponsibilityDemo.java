package com.behavioral.chainofresponsibility;

public final class ChainOfResponsibilityDemo {

    private ChainOfResponsibilityDemo() {
    }

    public static void run() {
        System.out.println("=== Chain of Responsibility ===");

        Approver manager = new Manager();
        Approver director = new Director();
        Approver vicePresident = new VicePresident();

        manager.setNext(director).setNext(vicePresident);

        manager.process(new PurchaseRequest(500, "office supplies"));
        manager.process(new PurchaseRequest(5_000, "new laptops"));
        manager.process(new PurchaseRequest(25_000, "conference sponsorship"));
        manager.process(new PurchaseRequest(100_000, "new office lease"));
    }
}
