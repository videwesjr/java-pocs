package com.behavioral.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public final class History {

    private final Deque<EditorMemento> snapshots = new ArrayDeque<>();

    public void push(EditorMemento memento) {
        snapshots.push(memento);
    }

    public EditorMemento pop() {
        return snapshots.pop();
    }
}
