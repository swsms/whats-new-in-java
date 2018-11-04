package org.avb.whatsnew.java10.optional;

import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Since Java 10, the Optional class has orElseThrow that does not have an argument
 * and throws NoSuchElementException.
 *
 * It overloads an already existing method that takes an exception supplier.
 */
public class OrElseThrowDemo {

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testOrElseThrow() {
        Optional.empty()
                .orElseThrow();
    }
}
