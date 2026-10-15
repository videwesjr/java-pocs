package com.java14.textblocks;

/**
 * JEP 368: Text Blocks, second preview (Java 14).
 *
 * Adds two escape sequences on top of Java 13's text blocks:
 *   backslash at end of line  joins the next line, for long single-line strings
 *   \s                        a single space that is not stripped as trailing whitespace
 */
public class TextBlockEscapesDemo {

    public static void main(String[] args) {
        String sql = """
                SELECT id, name, email \
                FROM customers \
                WHERE active = true \
                ORDER BY name""";
        System.out.println("Joined into one line:");
        System.out.println(sql);
        System.out.println("Contains a newline: " + sql.contains("\n"));

        // Trailing spaces are normally stripped; \s keeps the columns aligned.
        String table = """
                red  \s
                green\s
                blue \s
                """;
        System.out.println();
        System.out.println("Fixed-width rows (| marks the end):");
        table.lines().forEach(line -> System.out.println(line + "|"));
    }
}
