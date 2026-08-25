package com.behavioral.state;

public final class ModerationState implements DocumentState {

    @Override
    public void publish(Document document) {
        if (document.isAdmin()) {
            document.setState(new PublishedState());
        } else {
            System.out.println("Still awaiting moderator approval.");
        }
    }

    @Override
    public String name() {
        return "Moderation";
    }
}
