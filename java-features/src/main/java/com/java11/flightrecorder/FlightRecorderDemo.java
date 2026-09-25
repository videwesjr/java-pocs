package com.java11.flightrecorder;

import java.util.ArrayList;
import java.util.List;

/**
 * JEP 328: Flight Recorder.
 *
 * JFR, formerly a commercial-only feature, became free and open source in
 * Java 11. It's a low-overhead event-collection framework built into the JVM
 * for profiling and diagnosing production applications.
 *
 * Record a run of this class with:
 *   java -XX:StartFlightRecording=filename=demo.jfr,duration=30s \
 *        com.java11.flightrecorder.FlightRecorderDemo
 *
 * Then inspect demo.jfr with `jfr print demo.jfr` or open it in JDK Mission Control.
 */
public class FlightRecorderDemo {

    public static void main(String[] args) throws InterruptedException {
        List<int[]> garbage = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            garbage.add(new int[1_000_000]); // generate allocation/GC activity for JFR to record
            busyLoop();
            Thread.sleep(50);
        }
        System.out.println("Done. Allocated " + garbage.size() + " arrays to generate recordable activity.");
    }

    private static void busyLoop() {
        long sum = 0;
        for (int i = 0; i < 5_000_000; i++) {
            sum += i;
        }
        if (sum < 0) { // never true, keeps the JIT from eliding the loop
            System.out.println(sum);
        }
    }
}
