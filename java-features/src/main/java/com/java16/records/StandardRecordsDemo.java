package com.java16.records;

import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * JEP 395: Records (standard in Java 16).
 *
 * Preview in Java 14 (JEP 359) and Java 15 (JEP 384). Java 16 finalized the
 * feature unchanged, so records, local records and the reflection API
 * (Class::isRecord, Class::getRecordComponents) work without
 * --enable-preview. The relaxation that came with it -- inner classes may
 * declare static members -- is a language change in its own right and
 * applies to anything static, not just records.
 *
 * A record fixes the API derived from its component list; everything else is
 * still an ordinary class, so it can implement interfaces, declare static
 * factories, override the generated members, and validate or normalize its
 * state in the canonical constructor.
 */
public class StandardRecordsDemo {

    /** Records can be generic and can implement interfaces. */
    record Range<T extends Comparable<T>>(T low, T high) implements Comparable<Range<T>> {

        Range {
            if (low.compareTo(high) > 0) {
                throw new IllegalArgumentException("low must not exceed high: " + low + " > " + high);
            }
        }

        boolean contains(T value) {
            return low.compareTo(value) <= 0 && high.compareTo(value) >= 0;
        }

        @Override
        public int compareTo(Range<T> other) {
            return Comparator.<Range<T>, T>comparing(Range::low)
                    .thenComparing(Range::high)
                    .compare(this, other);
        }
    }

    /**
     * A record is shallowly immutable: the reference is final, the object it
     * points at need not be. Copying in the canonical constructor and out of
     * the accessor is what makes the component genuinely immutable.
     */
    record Order(String id, List<String> items) {

        // Explicit canonical constructor: the copy has to happen before the
        // field is assigned, so the generated accessor can hand the list out
        // without copying again.
        Order(String id, List<String> items) {
            this.id = id;
            this.items = List.copyOf(items);
        }
    }

    /** An inner, non-static class -- which since Java 16 may hold static members. */
    class Shipment {
        static final String CARRIER = "post";

        record Label(String orderId, String address) {
        }
    }

    public static void main(String[] args) {
        genericRecord();
        defensiveCopies();
        staticMembersInInnerClass();
        reflection();
    }

    private static void genericRecord() {
        List<Range<Integer>> ranges = new ArrayList<>(List.of(
                new Range<>(10, 20),
                new Range<>(1, 5),
                new Range<>(10, 15)));
        ranges.sort(Comparator.naturalOrder());

        System.out.println("sorted ranges: " + ranges);
        System.out.println("Range(1,5) contains 3: " + new Range<>(1, 5).contains(3));

        try {
            new Range<>("z", "a");
        } catch (IllegalArgumentException e) {
            System.out.println("validation: " + e.getMessage());
        }
    }

    private static void defensiveCopies() {
        List<String> mutable = new ArrayList<>(List.of("book", "pen"));
        Order order = new Order("O-1", mutable);

        mutable.add("eraser"); // the caller's list, not the record's

        System.out.println();
        System.out.println("order after caller mutated its own list: " + order);

        try {
            order.items().add("smuggled");
        } catch (UnsupportedOperationException e) {
            System.out.println("accessor returns an unmodifiable view: " + e.getClass().getSimpleName());
        }
    }

    private static void staticMembersInInnerClass() {
        // Legal since Java 16: Label is a static member of the inner class
        // Shipment, so it needs no enclosing instance.
        Shipment.Label label = new Shipment.Label("O-1", "1 Java Way");

        System.out.println();
        System.out.println("static member of an inner class: " + label + " via " + Shipment.CARRIER);
    }

    private static void reflection() {
        System.out.println();
        System.out.println("Order.class.isRecord(): " + Order.class.isRecord());
        for (RecordComponent component : Order.class.getRecordComponents()) {
            System.out.printf("  %-6s type=%-6s generic=%-26s accessor=%s%n",
                    component.getName(),
                    component.getType().getSimpleName(),
                    component.getGenericType(),
                    component.getAccessor().getName());
        }
    }
}
