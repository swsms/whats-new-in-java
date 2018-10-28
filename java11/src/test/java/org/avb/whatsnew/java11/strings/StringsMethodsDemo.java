package org.avb.whatsnew.java11.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringsMethodsDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringsMethodsDemo.class);

    @Test
    public void testRepeatSmile() {
        var smiles = ":)".repeat(10); // ":):):):):):):):):):)"
        LOGGER.info("smiles: {}", smiles);
    }

    @Test
    public void testIsBlankString() {
        var emptyString = "";
        assertTrue(emptyString.isBlank());

        var stringWithSpaces = "  \t\n";
        assertTrue(stringWithSpaces.isBlank());

        var stringWithLetters = "  abc  ";
        assertFalse(stringWithLetters.isBlank());
    }

    @Test
    public void testLines() {
        var languages = "java\nkotlin\nscala\nclosure";
        languages.lines()
                .forEach(LOGGER::info);  // prints each word from a new line
    }

    @Test
    public void testStripMethods() {
        LOGGER.info("\n\t abc".stripLeading()); // "abc"
        LOGGER.info("def\n\t ".stripTrailing()); // "def"
        LOGGER.info(" \n\tqq\n\t ".strip()); // "qq"

        var stringWithMagicSpace = "\u205Fabc";

        LOGGER.info(stringWithMagicSpace.strip()); // "abc"
        LOGGER.info(stringWithMagicSpace.trim());  // " abc"
    }
}
