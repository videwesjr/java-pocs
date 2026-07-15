package com.creational.factorymethod;

public final class FactoryMethodDemo {

    private FactoryMethodDemo() {
    }

    public static void run() {
        System.out.println("=== Factory Method ===");

        NotificationFactory emailFactory = new EmailNotificationFactory();
        NotificationFactory smsFactory = new SmsNotificationFactory();

        emailFactory.notify("Your order has shipped");
        smsFactory.notify("Your order has shipped");
    }
}
