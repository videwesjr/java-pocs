package com.behavioral.strategy;

public final class CreditCardPayment implements PaymentStrategy {

    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amountCents) {
        String lastFour = cardNumber.substring(cardNumber.length() - 4);
        System.out.printf("Charged $%.2f to credit card ending in %s%n", amountCents / 100.0, lastFour);
    }

    @Override
    public String name() {
        return "Credit Card";
    }
}
