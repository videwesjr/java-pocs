package com.behavioral.visitor;

public final class Electronics implements ShoppingItem {

    private final String name;
    private final int priceCents;
    private final int warrantyYears;

    public Electronics(String name, int priceCents, int warrantyYears) {
        this.name = name;
        this.priceCents = priceCents;
        this.warrantyYears = warrantyYears;
    }

    public String getName() {
        return name;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }

    @Override
    public void accept(ShoppingItemVisitor visitor) {
        visitor.visit(this);
    }
}
