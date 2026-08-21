package com.behavioral.memento;

public final class MementoDemo {

    private MementoDemo() {
    }

    public static void run() {
        System.out.println("=== Memento ===");

        TextEditor editor = new TextEditor();
        History history = new History();

        editor.write("Hello");
        history.push(editor.save());

        editor.write(", world!");
        history.push(editor.save());

        editor.write(" This part will be undone.");
        System.out.println("Current: " + editor.getContent());

        editor.restore(history.pop());
        System.out.println("After undo: " + editor.getContent());
    }
}
