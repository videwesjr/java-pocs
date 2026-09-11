package com.extra.specification;

import java.util.List;

public final class SpecificationDemo {

    private SpecificationDemo() {
    }

    public static void run() {
        System.out.println("=== Specification ===");

        List<Product> products = List.of(
                new Product("Keyboard", 45.0, true),
                new Product("Monitor", 320.0, true),
                new Product("Mouse", 25.0, false));

        Specification<Product> affordableAndAvailable =
                new InStockSpecification().and(new PriceBelowSpecification(100.0));

        for (Product product : products) {
            boolean matches = affordableAndAvailable.isSatisfiedBy(product);
            System.out.println(product + " -> matches=" + matches);
        }
    }
}
