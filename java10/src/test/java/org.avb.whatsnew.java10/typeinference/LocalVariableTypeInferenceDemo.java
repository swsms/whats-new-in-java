package org.avb.whatsnew.java10.typeinference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;

/**
 * Java 10 extends type inference for local variables using var.
 * This feature totally reduces the number of boilerplate code.
 *
 * Important, this type inference is available only for local variables with the initializer.
 * It does not work for member variables, method parameters, return types.
 * It also does not work without initialization.
 */
public class LocalVariableTypeInferenceDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalVariableTypeInferenceDemo.class);

    @Test
    public void testTypeInferenceInSimpleCases() {
        var zero = 0; // int
        var one = 1L; // long

        LOGGER.info("zero is denoted as {}, one is denoted as {}", zero, one);

        var msg = "How are you today?"; // String
        var today = LocalDate.now(); // LocalDate

        LOGGER.info("{}: {}", today, msg);
    }

    @Test
    public void testTypeInferenceWithGenerics() {
        var persons = List.of( // var replaces List<Person>
                new Person("John", 34),
                new Person("Margo", 28),
                new Person("Alex", 18),
                new Person("John", 22),
                new Person("Katy", 34),
                new Person("Paul", 28)
        );

        LOGGER.info("Persons: {}", persons);

        var namesByAges = persons.stream() // var replaces Map<Integer, List<String>>
                .collect(
                        groupingBy(
                                Person::getAge,
                                TreeMap::new,
                                mapping(Person::getName,
                                        toList())));

        namesByAges.forEach((age, names) -> LOGGER.info("{}: {}", age, names));

        /* Sorted output cause we use TreeMap
         * 18: [Alex]
         * 22: [John]
         * 28: [Margo, Paul]
         * 34: [John, Katy]
         */
    }

    @Test
    public void testTypeInferenceWithTryWithResources() throws IOException {
        var bytes = new byte[] { 0x01, 0x02, 0x03 };

        try (var output = new ByteArrayOutputStream();
             var input = new ByteArrayInputStream(bytes)) {
            LOGGER.info("Inside try-with-resources");
            input.transferTo(output);
        }
    }
}

class Person {
    private final String name;
    private final int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}