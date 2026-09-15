package com.extra.dependencyinjection;

public final class EmailMessageService implements MessageService {

    @Override
    public void send(String message, String recipient) {
        System.out.println("Emailing '" + message + "' to " + recipient);
    }
}
