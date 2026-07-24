package com.structural.composite;

public final class CompositeDemo {

    private CompositeDemo() {
    }

    public static void run() {
        System.out.println("=== Composite ===");

        Directory root = new Directory("root");

        Directory src = new Directory("src");
        src.add(new File("Main.java", 1200));
        src.add(new File("Utils.java", 800));

        Directory docs = new Directory("docs");
        docs.add(new File("README.md", 300));

        root.add(src);
        root.add(docs);
        root.add(new File(".gitignore", 50));

        root.print("");
        System.out.println("Total size: " + root.getSize() + " bytes");
    }
}
