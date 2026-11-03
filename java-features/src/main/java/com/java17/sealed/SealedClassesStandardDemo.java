package com.java17.sealed;

import java.util.Arrays;
import java.util.List;

/**
 * JEP 409: Sealed Classes (standard in Java 17).
 *
 * Preview in Java 15 (JEP 360) and Java 16 (JEP 397). Java 17 finalized the
 * feature unchanged, so sealed, non-sealed and permits are now part of the
 * language proper.
 *
 * The earlier demos seal an interface. This one seals an abstract class as
 * well, which is where the modifier earns its place: the base can carry
 * state and a constructor that only its permitted subclasses may call, so
 * the hierarchy stays closed without hiding the base behind package-private
 * access. Sealing is also transitive in practice -- a permitted subclass may
 * seal its own branch, giving a hierarchy that is closed at every level.
 *
 * Because the compiler knows the whole hierarchy, it rejects a cast that no
 * permitted subtype could ever satisfy:
 *
 *   Payment payment = ...;
 *   Runnable runnable = (Runnable) payment;
 *   // error: incompatible types: Payment cannot be converted to Runnable
 *
 * Without sealing that cast would compile and fail at run time instead.
 */
public class SealedClassesStandardDemo {

    /** Level 1: a sealed interface, closed to three branches. */
    sealed interface Payment permits Card, Transfer, Voucher {
        double amount();
    }

    /** Level 2: a sealed abstract class, closed to two leaves of its own. */
    abstract static sealed class Card implements Payment permits CreditCard, DebitCard {

        private final String last4;
        private final double amount;

        // Reachable only from the permitted subclasses, by construction.
        protected Card(String last4, double amount) {
            this.last4 = last4;
            this.amount = amount;
        }

        @Override
        public final double amount() {
            return amount;
        }

        final String last4() {
            return last4;
        }

        abstract String network();

        @Override
        public String toString() {
            return getClass().getSimpleName() + "[" + network() + " ****" + last4 + "]";
        }
    }

    /** Level 3: the leaves must be final, sealed or non-sealed. */
    static final class CreditCard extends Card {

        private final int installments;

        CreditCard(String last4, double amount, int installments) {
            super(last4, amount);
            this.installments = installments;
        }

        @Override
        String network() {
            return "credit";
        }

        double installmentValue() {
            return amount() / installments;
        }
    }

    static final class DebitCard extends Card {

        DebitCard(String last4, double amount) {
            super(last4, amount);
        }

        @Override
        String network() {
            return "debit";
        }
    }

    record Transfer(String iban, double amount) implements Payment {
    }

    record Voucher(String code, double amount) implements Payment {
    }

    public static void main(String[] args) {
        List<Payment> payments = List.of(
                new CreditCard("4321", 300.00, 3),
                new DebitCard("8765", 75.50),
                new Transfer("BR4712345", 1_200.00),
                new Voucher("WELCOME10", 10.00));

        payments.forEach(payment ->
                System.out.printf("%-34s %8.2f  fee %5.2f%n", payment, payment.amount(), fee(payment)));

        System.out.printf("%nsettled total: %.2f%n", payments.stream().mapToDouble(Payment::amount).sum());

        describeHierarchy();
    }

    /**
     * Dispatch over a closed hierarchy. The trailing throw is dead code the
     * compiler still demands, because exhaustiveness checking only arrives
     * with pattern matching for switch (JEP 406, previewed in this same
     * release).
     */
    private static double fee(Payment payment) {
        if (payment instanceof CreditCard credit) {
            return credit.amount() * 0.03 + credit.installments * 0.50;
        }
        if (payment instanceof DebitCard debit) {
            return debit.amount() * 0.01;
        }
        if (payment instanceof Transfer transfer) {
            return transfer.iban().startsWith("BR") ? 1.50 : 12.00;
        }
        if (payment instanceof Voucher) {
            return 0.0;
        }
        throw new IllegalStateException("unreachable: Payment permits nothing else");
    }

    private static void describeHierarchy() {
        System.out.println();
        for (Class<?> type : List.of(Payment.class, Card.class, CreditCard.class)) {
            System.out.printf("%-12s sealed=%-6s permits=%s%n",
                    type.getSimpleName(),
                    type.isSealed(),
                    type.isSealed()
                            ? Arrays.stream(type.getPermittedSubclasses()).map(Class::getSimpleName).toList()
                            : "(final leaf)");
        }
    }
}
