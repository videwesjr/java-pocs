package com.java12.compactnumber;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * API added in Java 12: java.text.CompactNumberFormat, exposed via
 * NumberFormat.getCompactNumberInstance. Formats numbers the way humans
 * abbreviate them (1.2K, 3M, ...) instead of printing every digit.
 */
public class CompactNumberFormatDemo {

    public static void main(String[] args) {
        NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        NumberFormat longFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);

        for (int value : new int[]{950, 1_200, 24_000, 3_400_000}) {
            System.out.println(value + " -> short: " + shortFormat.format(value) + ", long: " + longFormat.format(value));
        }
    }
}
