package com.java12.shenandoah;

/**
 * JEP 189: Shenandoah, a low-pause-time garbage collector (experimental,
 * contributed by Red Hat; first shipped as experimental in JDK 12).
 *
 * Shenandoah does most of its work concurrently with the application
 * threads, keeping pause times roughly constant regardless of heap size.
 *
 * Run with (requires a JDK build that ships Shenandoah):
 *   java -XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC \
 *        com.java12.shenandoah.ShenandoahGcDemo
 *
 * Compare pause times against the default collector, e.g. by adding -Xlog:gc
 * to both runs.
 */
public class ShenandoahGcDemo {

    public static void main(String[] args) {
        for (int round = 0; round < 10; round++) {
            allocateChurn();
            System.out.println("Round " + round + " complete");
        }
    }

    private static void allocateChurn() {
        for (int i = 0; i < 200_000; i++) {
            byte[] shortLived = new byte[256];
            if (shortLived.length < 0) { // never true, keeps the JIT from eliding the allocation
                throw new IllegalStateException();
            }
        }
    }
}
