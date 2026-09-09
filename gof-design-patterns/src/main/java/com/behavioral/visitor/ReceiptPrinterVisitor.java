package com.behavioral.visitor;

public final class ReceiptPrinterVisitor implements ShoppingItemVisitor {

    @Override
    public void visit(Book book) {
        System.out.printf("Book: %-20s $%.2f%n", book.getTitle(), book.getPriceCents() / 100.0);
    }

    @Override
    public void visit(Fruit fruit) {
        double subtotal = fruit.getPricePerKgCents() * fruit.getWeightKg() / 100.0;
        System.out.printf("Fruit: %-19s %.2fkg  $%.2f%n", fruit.getName(), fruit.getWeightKg(), subtotal);
    }

    @Override
    public void visit(Electronics electronics) {
        System.out.printf("Electronics: %-13s $%.2f (%d yr warranty)%n",
                electronics.getName(), electronics.getPriceCents() / 100.0, electronics.getWarrantyYears());
    }
}
