package com.behavioral.mediator;

public abstract class User {

    protected final ChatMediator mediator;
    protected final String name;

    protected User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void send(String message);

    public abstract void receive(String message, String senderName);
}
