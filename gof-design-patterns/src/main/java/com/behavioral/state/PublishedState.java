package com.behavioral.state;

public final class PublishedState implements DocumentState {

    @Override
    public void publish(Document document) {
        System.out.println("Already published.");
    }

    @Override
    public String name() {
        return "Published";
    }
}
