package org.avb.whatsnew.java11.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.*;

public class CollectionsMethodsDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionsMethodsDemo.class);

    @Test
    public void testToArrayWithGenerator() {
        var fibonacci = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
                .toArray(Integer[]::new);

        Arrays.stream(fibonacci)
                .map(Object::toString)
                .forEach(LOGGER::info); // prints items in the same order as in the list

        var languages = Set.of("Java", "Kotlin", "Scala")
                .toArray(String[]::new);

        Arrays.stream(languages)
                .map(Object::toString)
                .forEach(LOGGER::info); // prints items in any order
    }
}
