package com.behavioral.iterator;

public final class IteratorDemo {

    private IteratorDemo() {
    }

    public static void run() {
        System.out.println("=== Iterator ===");

        BookShelf bookShelf = new BookShelf();
        bookShelf.addBook(new Book("Design Patterns"));
        bookShelf.addBook(new Book("Clean Code"));
        bookShelf.addBook(new Book("Refactoring"));

        Iterator<Book> iterator = bookShelf.createIterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(book.getTitle());
        }
    }
}
