package com.java15.records;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.RecordComponent;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JEP 384: Records, 2nd preview (Java 15).
 *
 * The second preview kept the syntax of JEP 359 and relaxed the surrounding
 * rules:
 *  - local records, and alongside them local enums and local interfaces;
 *  - static members in inner classes, which is what makes a record
 *    declarable inside an inner class at all (records are implicitly static);
 *  - annotations on record components, which are propagated to the field,
 *    the accessor and the constructor parameter;
 *  - records may implement sealed interfaces (JEP 360), which is where
 *    records start doubling as algebraic data types.
 */
public class RecordsSecondPreviewDemo {

    @Target({ElementType.RECORD_COMPONENT, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Identifier {
    }

    record Account(@Identifier String id, String owner, double balance) {
    }

    public static void main(String[] args) throws ReflectiveOperationException {
        localRecord();
        localEnumAndInterface();
        componentAnnotations();
    }

    /**
     * A local record is the natural carrier for an intermediate tuple: it
     * lives inside the method that needs it instead of leaking into the
     * enclosing class as a nested type.
     */
    private static void localRecord() {
        List<Account> accounts = List.of(
                new Account("A-1", "Duke", 120.50),
                new Account("A-2", "Gosling", 4_000.00),
                new Account("A-3", "Duke", 80.00));

        // Declared inside the method, used only by the pipeline below.
        record OwnerTotal(String owner, double total) {
        }

        List<OwnerTotal> totals = accounts.stream()
                .collect(Collectors.groupingBy(
                        Account::owner,
                        Collectors.summingDouble(Account::balance)))
                .entrySet().stream()
                .map(e -> new OwnerTotal(e.getKey(), e.getValue()))
                .sorted(Comparator.comparingDouble(OwnerTotal::total).reversed())
                .toList();

        System.out.println("local record results:");
        totals.forEach(t -> System.out.printf("  %-8s %10.2f%n", t.owner(), t.total()));
    }

    /**
     * Local enums and local interfaces were allowed by the same JEP; before
     * Java 15 only local classes were.
     */
    private static void localEnumAndInterface() {
        enum Tier {
            BASIC, PREMIUM
        }

        interface Fee {
            double monthly(Tier tier);
        }

        Fee fee = tier -> tier == Tier.PREMIUM ? 19.90 : 4.90;

        System.out.println();
        System.out.println("local enum + local interface:");
        for (Tier tier : Tier.values()) {
            System.out.printf("  %-8s %5.2f%n", tier, fee.monthly(tier));
        }
    }

    /**
     * An annotation on a record component is copied to every declaration the
     * compiler derives from it, as long as the annotation's @Target allows
     * that element kind.
     */
    private static void componentAnnotations() throws ReflectiveOperationException {
        RecordComponent[] components = Account.class.getRecordComponents();

        System.out.println();
        System.out.println("Account.class.isRecord(): " + Account.class.isRecord());
        for (RecordComponent component : components) {
            System.out.printf("  %-8s %-16s annotations=%d%n",
                    component.getName(),
                    component.getType().getSimpleName(),
                    component.getAnnotations().length);
        }

        Map<String, Boolean> propagated = Map.of(
                "field", Account.class.getDeclaredField("id")
                        .isAnnotationPresent(Identifier.class),
                "accessor", Account.class.getDeclaredMethod("id")
                        .isAnnotationPresent(Identifier.class),
                "constructor parameter", Account.class
                        .getDeclaredConstructor(String.class, String.class, double.class)
                        .getParameters()[0]
                        .isAnnotationPresent(Identifier.class));

        System.out.println("@Identifier propagated to:");
        propagated.forEach((target, present) -> System.out.println("  " + target + ": " + present));
    }
}
