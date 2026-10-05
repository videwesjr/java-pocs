package com.java13.textblocks;

/**
 * JEP 355: Text Blocks (preview in Java 13, standard since Java 15).
 *
 * Multi-line string literals delimited by triple quotes. Incidental
 * indentation is stripped based on the least-indented line (including the
 * closing delimiter), and line terminators are normalized to \n.
 *
 * Java 13 also added companion String methods (preview APIs at the time,
 * standard since Java 15): stripIndent, translateEscapes and formatted.
 */
public class TextBlocksDemo {

    public static void main(String[] args) {
        String concatenated = "{\n"
                + "  \"name\": \"Duke\",\n"
                + "  \"version\": 13\n"
                + "}\n";

        String textBlock = """
                {
                  "name": "Duke",
                  "version": 13
                }
                """;

        System.out.println(textBlock);
        System.out.println("Same content as concatenation: " + textBlock.equals(concatenated));

        String html = """
                <html>
                    <body>
                        <p>Hello, text blocks</p>
                    </body>
                </html>""";
        System.out.println(html);

        helperMethods();
    }

    private static void helperMethods() {
        System.out.println();

        // formatted: instance-method shorthand for String.format.
        String greeting = """
                Hello, %s!
                You are running Java %d.
                """.formatted("Duke", Runtime.version().feature());
        System.out.print(greeting);

        // stripIndent: the same indentation stripping text blocks apply at
        // compile time, available at runtime for strings built elsewhere.
        String indented = "      first\n        second\n      third";
        System.out.println("stripIndent:\n" + indented.stripIndent());

        // translateEscapes: interpret escape sequences in a string read as raw text.
        String raw = "tab:\\t| newline:\\n| quote:\\\"";
        System.out.println("raw:              " + raw);
        System.out.println("translateEscapes: " + raw.translateEscapes());
    }
}
