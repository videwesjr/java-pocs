package com.extra.specification;

public final class Product {

    private final String name;
    private final double price;
    private final boolean inStock;

    public Product(String name, double price, boolean inStock) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isInStock() {
        return inStock;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ", inStock=" + inStock + ")";
    }
}
