package com.java13.textblocks;

/**
 * JEP 355: Text Blocks (preview in Java 13, standard since Java 15).
 *
 * Multi-line string literals delimited by triple quotes. Incidental
 * indentation is stripped based on the least-indented line (including the
 * closing delimiter), and line terminators are normalized to \n.
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
    }
}
