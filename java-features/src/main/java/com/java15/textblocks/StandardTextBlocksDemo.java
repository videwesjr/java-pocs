package com.java15.textblocks;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * JEP 378: Text Blocks (standard in Java 15).
 *
 * Preview in Java 13 (JEP 355) and Java 14 (JEP 368). Java 15 finalized the
 * feature without further syntax changes, so text blocks and the companion
 * String methods (formatted, stripIndent, translateEscapes) no longer need
 * --enable-preview.
 *
 * Standard also means a text block is just a string literal to the rest of
 * the language: it is a compile-time constant, so it is interned, folded
 * into constant expressions and accepted anywhere a constant is required.
 */
public class StandardTextBlocksDemo {

    // A constant variable, exactly as if written as a single-line literal.
    private static final String HEADER = """
            id,name,balance
            """;

    @Retention(RetentionPolicy.RUNTIME)
    @interface Query {
        String value();
    }

    public static void main(String[] args) {
        constantExpression();
        incidentalIndentation();
        annotationElement();
    }

    private static void constantExpression() {
        String block = """
                yes""";

        // Interning proves the compiler treated the text block as a literal.
        System.out.println("interned with an equal literal: " + (block == "yes"));

        // Constant folding: concatenating two constants happens at compile time.
        String report = HEADER + """
                1,Duke,10.00
                """;
        System.out.print(report);
    }

    private static void incidentalIndentation() {
        // The closing delimiter counts as one of the lines when the compiler
        // computes the common (incidental) indentation to strip.
        String aligned = """
                alpha
                  beta
                """;

        String outdented = """
                alpha
                  beta
        """;

        System.out.println("closing delimiter aligned:  " + visible(aligned));
        System.out.println("closing delimiter outdented: " + visible(outdented));
    }

    private static void annotationElement() {
        Query query = annotated();
        System.out.println("annotation value:\n" + query.value());
    }

    @Query("""
            select id, name, balance
            from account
            where balance > 0
            """)
    private static Query annotated() {
        try {
            return StandardTextBlocksDemo.class
                    .getDeclaredMethod("annotated")
                    .getAnnotation(Query.class);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    private static List<String> visible(String text) {
        return text.lines().map(line -> "[" + line + "]").toList();
    }
}
