package com.behavioral.command;

public class Light {

    private final String room;

    public Light(String room) {
        this.room = room;
    }

    public void on() {
        System.out.printf("%s light is ON%n", room);
    }

    public void off() {
        System.out.printf("%s light is OFF%n", room);
    }
}
