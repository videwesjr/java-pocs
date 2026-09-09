package com.behavioral.visitor;

public final class Book implements ShoppingItem {

    private final String title;
    private final int priceCents;

    public Book(String title, int priceCents) {
        this.title = title;
        this.priceCents = priceCents;
    }

    public String getTitle() {
        return title;
    }

    public int getPriceCents() {
        return priceCents;
    }

    @Override
    public void accept(ShoppingItemVisitor visitor) {
        visitor.visit(this);
    }
}
