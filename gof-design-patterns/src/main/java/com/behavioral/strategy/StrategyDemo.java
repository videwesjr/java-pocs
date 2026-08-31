package com.behavioral.strategy;

public final class StrategyDemo {

    private StrategyDemo() {
    }

    public static void run() {
        System.out.println("=== Strategy ===");

        ShoppingCart cart = new ShoppingCart(4599);

        cart.setPaymentStrategy(new CreditCardPayment("4111111111111234"));
        cart.checkout();

        cart.setPaymentStrategy(new PayPalPayment("shopper@example.com"));
        cart.checkout();

        cart.setPaymentStrategy(new GiftCardPayment(5000));
        cart.checkout();
    }
}
