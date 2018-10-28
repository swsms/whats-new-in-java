package org.avb.whatsnew.java11.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
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
}
