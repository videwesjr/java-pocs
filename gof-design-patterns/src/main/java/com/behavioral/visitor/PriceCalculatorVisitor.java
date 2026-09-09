package com.behavioral.visitor;

public final class PriceCalculatorVisitor implements ShoppingItemVisitor {

    private static final double ELECTRONICS_TAX_RATE = 0.08;
    private static final int WARRANTY_YEAR_FEE_CENTS = 500;

    private int totalCents;

    @Override
    public void visit(Book book) {
        totalCents += book.getPriceCents();
    }

    @Override
    public void visit(Fruit fruit) {
        totalCents += Math.round(fruit.getPricePerKgCents() * fruit.getWeightKg());
    }

    @Override
    public void visit(Electronics electronics) {
        int tax = (int) Math.round(electronics.getPriceCents() * ELECTRONICS_TAX_RATE);
        int warrantyFee = electronics.getWarrantyYears() * WARRANTY_YEAR_FEE_CENTS;
        totalCents += electronics.getPriceCents() + tax + warrantyFee;
    }

    public int getTotalCents() {
        return totalCents;
    }
}
