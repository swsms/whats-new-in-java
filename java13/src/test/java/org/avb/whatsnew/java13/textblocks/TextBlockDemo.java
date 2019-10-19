package org.avb.whatsnew.java13.textblocks;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Java 13 introduces text blocks that simplify the declaration of multi-line strings.
 * We do not use need to write newline characters (\n) and mark quotes with backslash (\")
 * inside such blocks.
 */
public class TextBlockDemo {

    /* It looks ugly and unreadable! */
    private static final String rawStringHelloWorldProgram =
            "public class Main {\n" +
            "    public static void main(String[] args) {\n" +
            "        System.out.println(\"Hello, world!\");\n" +
            "    }\n" +
            "}";

    @Test
    public void testTextBlockWithInternalQuotes() {
        String helloWorldProgram = """
        public class Main {
            public static void main(String[] args) {
                System.out.println("Hello, world!");
            }
        }""";
        assertEquals(helloWorldProgram, rawStringHelloWorldProgram);
    }

    @Test
    public void testTextBlockWithMultipleLineBreaks() {
        String textWithNumbers = """
        1

        2

        3""";
        assertEquals(textWithNumbers, "1\n\n2\n\n3");
    }
}
