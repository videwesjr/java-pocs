package com.extra.dependencyinjection;

public final class Notifier {

    private final MessageService messageService;

    public Notifier(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notify(String recipient) {
        messageService.send("Your order has shipped", recipient);
    }
}
