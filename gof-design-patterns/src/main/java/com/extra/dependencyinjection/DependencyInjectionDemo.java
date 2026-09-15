package com.extra.dependencyinjection;

public final class DependencyInjectionDemo {

    private DependencyInjectionDemo() {
    }

    public static void run() {
        System.out.println("=== Dependency Injection ===");

        Notifier emailNotifier = new Notifier(new EmailMessageService());
        emailNotifier.notify("john@example.com");

        Notifier smsNotifier = new Notifier(new SmsMessageService());
        smsNotifier.notify("+1-555-0100");
    }
}
