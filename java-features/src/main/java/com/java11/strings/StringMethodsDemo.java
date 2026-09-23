package com.java11.strings;

import java.util.stream.Collectors;

/**
 * New java.lang.String convenience methods added in Java 11: isBlank,
 * strip/stripLeading/stripTrailing (Unicode-aware trimming), lines, and repeat.
 */
public class StringMethodsDemo {

    public static void main(String[] args) {
        String blank = "   ";
        System.out.println("isBlank: " + blank.isBlank());

        String padded = "  Java 11  ";
        System.out.println("strip: '" + padded.strip() + "'");
        System.out.println("stripLeading: '" + padded.stripLeading() + "'");
        System.out.println("stripTrailing: '" + padded.stripTrailing() + "'");

        String multiline = "line1\nline2\nline3";
        String joined = multiline.lines().collect(Collectors.joining(", "));
        System.out.println("lines: " + joined);

        System.out.println("repeat: " + "ab".repeat(3));
    }
}
