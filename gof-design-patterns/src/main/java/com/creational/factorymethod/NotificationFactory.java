package com.creational.factorymethod;

public abstract class NotificationFactory {

    public abstract Notification createNotification();

    public void notify(String message) {
        createNotification().send(message);
    }
}
