package com.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public final class BookShelf implements Aggregate<Book> {

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public Book getBookAt(int index) {
        return books.get(index);
    }

    public int getLength() {
        return books.size();
    }

    @Override
    public Iterator<Book> createIterator() {
        return new BookShelfIterator(this);
    }
}
