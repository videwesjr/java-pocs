package com.java11.singlefile;

/**
 * JEP 330: Launch Single-File Source-Code Programs.
 *
 * Since Java 11, a .java file can be run directly without a separate
 * compile step, e.g. from this package's directory:
 *   java SingleFileLaunchDemo.java
 *
 * The launcher compiles the file in memory and runs it, which is handy for
 * scripts and quick experiments that don't need a build file.
 */
public class SingleFileLaunchDemo {

    public static void main(String[] args) {
        System.out.println("Hello from a class that can also be launched as `java SingleFileLaunchDemo.java`.");
        System.out.println("PID: " + ProcessHandle.current().pid());
    }
}
