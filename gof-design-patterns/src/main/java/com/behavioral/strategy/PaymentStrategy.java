package com.behavioral.strategy;

public interface PaymentStrategy {

    void pay(int amountCents);

    String name();
}
