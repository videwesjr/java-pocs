package com.extra.specification;

public final class PriceBelowSpecification implements Specification<Product> {

    private final double limit;

    public PriceBelowSpecification(double limit) {
        this.limit = limit;
    }

    @Override
    public boolean isSatisfiedBy(Product candidate) {
        return candidate.getPrice() < limit;
    }
}
