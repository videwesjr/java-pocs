package com.behavioral.strategy;

public final class ShoppingCart {

    private final int totalCents;
    private PaymentStrategy paymentStrategy;

    public ShoppingCart(int totalCents) {
        this.totalCents = totalCents;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout() {
        if (paymentStrategy == null) {
            throw new IllegalStateException("No payment strategy selected");
        }
        System.out.println("Paying with: " + paymentStrategy.name());
        paymentStrategy.pay(totalCents);
    }
}
