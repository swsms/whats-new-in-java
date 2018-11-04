package org.avb.whatsnew.java11.optional;

import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

/**
 * In Java 11, the Optional class has a small improvement - a new method called isEmpty
 * that returns true If a value is  not present, otherwise false.
 */
public class IsEmptyDemo {

    @Test
    public void testIsEmptyTrue() {
        assertTrue(Optional.empty().isEmpty());  // ok
        assertTrue(Optional.ofNullable(null).isEmpty());  // ok
    }

    @Test
    public void testIsEmptyFalse() {
        assertFalse(Optional.of(123).isEmpty()); // ok
    }
}
