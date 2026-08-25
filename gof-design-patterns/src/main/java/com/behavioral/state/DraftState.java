package com.behavioral.state;

public final class DraftState implements DocumentState {

    @Override
    public void publish(Document document) {
        document.setState(new ModerationState());
    }

    @Override
    public String name() {
        return "Draft";
    }
}
