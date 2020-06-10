package org.avb.whatsnew.java8.methodrefs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReferencesDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodReferencesDemo.class);

    @Test
    public void testUnaryOperatorMethodReference() {
        UnaryOperator<String> function = String::toUpperCase;

        LOGGER.info(function.apply("hi")); // HI
    }

    @Test
    public void testConsumerMethodReference() {
        Consumer<String> infoLogger = LOGGER::info;

        infoLogger.accept("hi"); // hi
    }

    @Test
    public void testConstructorMethodReference() {
        Supplier<String> emptyStringCreator = String::new;

        LOGGER.info(emptyStringCreator.get()); // ""
    }

    @Test
    public void testStaticMethodSupplierMethodReference() {
        Supplier<LocalDate> emptyStringCreator = LocalDate::now;

        LOGGER.info("{}", emptyStringCreator.get()); // a date like 2020-06-11
    }

    @Test
    public void testPredicateMethodReference() {
        Predicate<Character> isUpper = Character::isUpperCase;

        LOGGER.info("{}", isUpper.test('Q')); // true
        LOGGER.info("{}", isUpper.test('a')); // false
    }
}
