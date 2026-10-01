package com.java12.cds;

/**
 * JEP 341: Default CDS Archives.
 *
 * Class-Data Sharing archives pre-process the JDK's own classes into a
 * shared, memory-mappable archive to cut startup time and footprint. Before
 * Java 12 you had to generate this archive yourself; since Java 12 the JDK
 * ships with a default archive out of the box.
 *
 * Compare startup behavior with and without it:
 *   java -Xshare:off  com.java12.cds.DefaultCdsArchivesDemo   (CDS disabled)
 *   java -Xshare:auto com.java12.cds.DefaultCdsArchivesDemo   (default, uses the archive)
 *   java -Xlog:class+load com.java12.cds.DefaultCdsArchivesDemo
 *       (inspect the log for "shared" vs regular class loads)
 */
public class DefaultCdsArchivesDemo {

    public static void main(String[] args) {
        System.out.println("main() reached. Run with -Xshare:off vs -Xshare:auto and compare "
                + "process startup time (e.g. `time java ...`) or class-load logs.");
    }
}
