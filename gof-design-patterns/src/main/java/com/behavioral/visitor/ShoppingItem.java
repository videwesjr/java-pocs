package com.behavioral.visitor;

public interface ShoppingItem {

    void accept(ShoppingItemVisitor visitor);
}
