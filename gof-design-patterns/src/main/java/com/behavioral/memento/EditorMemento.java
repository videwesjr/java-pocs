package com.behavioral.memento;

public final class EditorMemento {

    private final String content;

    EditorMemento(String content) {
        this.content = content;
    }

    String getContent() {
        return content;
    }
}
