package com.extra.dependencyinjection;

public final class SmsMessageService implements MessageService {

    @Override
    public void send(String message, String recipient) {
        System.out.println("Texting '" + message + "' to " + recipient);
    }
}
