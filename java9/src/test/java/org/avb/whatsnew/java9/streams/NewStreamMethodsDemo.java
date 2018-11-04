package org.avb.whatsnew.java9.streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Stream;

public class NewStreamMethodsDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewStreamMethodsDemo.class);

    @Test
    public void testOfNullable() {
        List<String> list = null;
        Stream.ofNullable(list) // produces Stream.empty()
                .map(Object::toString)
                .forEach(LOGGER::info);
    }

    @Test
    public void testIterateWhileTrue() {
        Stream.iterate(0, i -> i < 5, i -> i + 1) // condition: i < 5
                .map(Object::toString)
                .forEach(LOGGER::info); // 0 1 2 3 4
    }

    @Test
    public void testTakeWhile() {
        List<Integer> numbers = List.of(2, 4, 6, 8, 9, 10, 12, 14);
        numbers.stream()
                .takeWhile(n -> n % 2 == 0)
                .forEach(n -> LOGGER.info("{}", n)); // 2 4 6 8
    }

    @Test
    public void testDropWhile() {
        List<Integer> numbers = List.of(2, 4, 6, 8, 9, 10, 12, 14);
        numbers.stream()
                .dropWhile(n -> n % 2 == 0)
                .forEach(n -> LOGGER.info("{}", n)); // 9 10 12 14
    }
}
