package com.behavioral.visitor;

public interface ShoppingItemVisitor {

    void visit(Book book);

    void visit(Fruit fruit);

    void visit(Electronics electronics);
}
