package com.behavioral.visitor;

public final class Fruit implements ShoppingItem {

    private final String name;
    private final int pricePerKgCents;
    private final double weightKg;

    public Fruit(String name, int pricePerKgCents, double weightKg) {
        this.name = name;
        this.pricePerKgCents = pricePerKgCents;
        this.weightKg = weightKg;
    }

    public String getName() {
        return name;
    }

    public int getPricePerKgCents() {
        return pricePerKgCents;
    }

    public double getWeightKg() {
        return weightKg;
    }

    @Override
    public void accept(ShoppingItemVisitor visitor) {
        visitor.visit(this);
    }
}
