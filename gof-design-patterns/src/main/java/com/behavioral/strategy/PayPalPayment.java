package com.behavioral.strategy;

public final class PayPalPayment implements PaymentStrategy {

    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amountCents) {
        System.out.printf("Charged $%.2f via PayPal account %s%n", amountCents / 100.0, email);
    }

    @Override
    public String name() {
        return "PayPal";
    }
}
