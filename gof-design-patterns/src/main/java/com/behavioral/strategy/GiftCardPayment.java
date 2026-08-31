package com.behavioral.strategy;

public final class GiftCardPayment implements PaymentStrategy {

    private int balanceCents;

    public GiftCardPayment(int balanceCents) {
        this.balanceCents = balanceCents;
    }

    @Override
    public void pay(int amountCents) {
        if (amountCents > balanceCents) {
            throw new IllegalStateException("Insufficient gift card balance");
        }
        balanceCents -= amountCents;
        System.out.printf("Charged $%.2f to gift card, remaining balance $%.2f%n",
                amountCents / 100.0, balanceCents / 100.0);
    }

    @Override
    public String name() {
        return "Gift Card";
    }
}
