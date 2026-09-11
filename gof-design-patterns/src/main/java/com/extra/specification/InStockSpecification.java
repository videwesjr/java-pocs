package com.extra.specification;

public final class InStockSpecification implements Specification<Product> {

    @Override
    public boolean isSatisfiedBy(Product candidate) {
        return candidate.isInStock();
    }
}
