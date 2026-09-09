package com.behavioral.visitor;

import java.util.List;

public final class VisitorDemo {

    private VisitorDemo() {
    }

    public static void run() {
        System.out.println("=== Visitor ===");

        List<ShoppingItem> cart = List.of(
                new Book("Design Patterns", 4499),
                new Fruit("Apples", 299, 1.5),
                new Electronics("Headphones", 8999, 2));

        ReceiptPrinterVisitor printer = new ReceiptPrinterVisitor();
        for (ShoppingItem item : cart) {
            item.accept(printer);
        }

        PriceCalculatorVisitor calculator = new PriceCalculatorVisitor();
        for (ShoppingItem item : cart) {
            item.accept(calculator);
        }
        System.out.printf("Total: $%.2f%n", calculator.getTotalCents() / 100.0);
    }
}
