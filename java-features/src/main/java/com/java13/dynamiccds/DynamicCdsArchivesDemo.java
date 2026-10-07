package com.java13.dynamiccds;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JEP 350: Dynamic CDS Archives.
 *
 * Java 12 shipped a default CDS archive for JDK classes only. Java 13 lets
 * you dump an archive at application exit that also includes the classes
 * your application loaded, with no class list needed up front.
 *
 * CDS only archives application classes loaded from a JAR, not from a class
 * directory, so package the compiled classes first (e.g. ./gradlew jar).
 *
 *   1. Record: java -XX:ArchiveClassesAtExit=app.jsa -cp app.jar com.java13.dynamiccds.DynamicCdsArchivesDemo
 *   2. Reuse:  java -XX:SharedArchiveFile=app.jsa -cp app.jar com.java13.dynamiccds.DynamicCdsArchivesDemo
 *   3. Verify: add -Xlog:class+load and look for "shared objects file (top)"
 */
public class DynamicCdsArchivesDemo {

    record Order(String customer, int amount) {
    }

    public static void main(String[] args) {
        // Touch a handful of classes (records, streams, collectors) so the
        // dynamic archive has application-specific content to capture.
        List<Order> orders = List.of(
                new Order("alice", 30),
                new Order("bob", 12),
                new Order("alice", 8));

        Map<String, Integer> totals = orders.stream()
                .collect(Collectors.groupingBy(Order::customer, Collectors.summingInt(Order::amount)));

        System.out.println("Totals per customer: " + totals);
    }
}
