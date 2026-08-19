package com.behavioral.mediator;

public final class MediatorDemo {

    private MediatorDemo() {
    }

    public static void run() {
        System.out.println("=== Mediator ===");

        ChatMediator chatRoom = new ChatRoom();

        User alice = new ChatUser(chatRoom, "Alice");
        User bob = new ChatUser(chatRoom, "Bob");
        User carol = new ChatUser(chatRoom, "Carol");

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(carol);

        alice.send("Hi everyone!");
        bob.send("Hey Alice!");
    }
}
