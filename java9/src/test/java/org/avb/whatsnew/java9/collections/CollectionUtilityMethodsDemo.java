package org.avb.whatsnew.java9.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Before Java 9 we could write Arrays.asList(...) to get a list with the specified elements.
 * We could also use methods like unmodifiableXXX, singletonXXX or emptyXXX to create unmodifiable lists, sets and maps.
 * But these methods are very tedious and verbose.
 *
 * Since Java 9 there is a set of convenient methods for creating immutable collections with the specified elements.
 * It returns an optimized implementation of a collection depending on the number of elements
 */
public class CollectionUtilityMethodsDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionUtilityMethodsDemo.class);

    @Test
    public void testListOf() {
        List<String> emptyList = List.of();
        LOGGER.info("{}", emptyList); // []

        List<String> singletonList = List.of("Orange");
        LOGGER.info("{}", singletonList); // [Orange]

        List<String> currencies = List.of("EUR", "USD", "CAD", "CNY");
        LOGGER.info("{}", currencies); // [EUR, USD, CAD, CNY]
    }

    @Test
    public void testSetOf() {
        Set<String> emptySet = Set.of();
        LOGGER.info("{}", emptySet); // []

        Set<String> singletonList = Set.of("Orange");
        LOGGER.info("{}", singletonList); // [Orange]

        Set<String> currencies = Set.of("EUR", "USD", "CAD", "CNY");
        LOGGER.info("{}", currencies); // Order can be any: [USD, CAD, CNY, EUR]
    }

    @Test
    public void testMapOf() {
        Map<Integer, String> emptyMap = Map.of();
        LOGGER.info("{}", emptyMap); // {}

        Map<String, String> oneAddress = Map.of("google.com", "172.217.22.174");
        LOGGER.info("{}", oneAddress); // {google.com=172.217.22.174}

        /* The format is: key, value, key, value, ... */
        Map<String, String> addresses = Map.of(
                "google.com", "172.217.22.174",
                "github.com", "192.30.253.112",
                "stackoverflow.com", "151.101.129.69"
        );

        /* {google.com=172.217.22.174, stackoverflow.com=151.101.129.69, github.com=192.30.253.112} */
        LOGGER.info("{}", addresses);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullNotAllowed() {
        List<Integer> digits = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, null);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testImmutability() {
        List<Integer> digits = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        digits.add(100); // it throws an exception
    }

    @Test
    public void testMapOfEntries() {
        Map<String,String> emptyMap = Map.ofEntries();
        LOGGER.info("{}", emptyMap); // {}

        Entry<String,String> nameToPhone1 = Map.entry("Darryl C. Boyd","1-651-643-4993");
        Entry<String,String> nameToPhone2 = Map.entry("Irene R. Jenkins","1-651-643-4993");
        LOGGER.info("{}", nameToPhone2); // Irene R. Jenkins=1-651-643-4993

        Map<String,String> nameToPhone = Map.ofEntries(nameToPhone1, nameToPhone2);

        /* {Darryl C. Boyd=1-651-643-4993, Irene R. Jenkins=1-651-643-4993} */
        LOGGER.info("{}", nameToPhone);
    }

    @Test
    public void testListOfSingleElementWithClass() {
        List<String> singleLanguageList = List.of("Java");

        /* java.util.ImmutableCollections$List12: [Java] */
        LOGGER.info("{}: {}", singleLanguageList.getClass(), singleLanguageList);
    }

    @Test
    public void testListOfTwoElementsWithClass() {
        List<String> twoLanguagesList = List.of("Java", "Kotlin");

        /* java.util.ImmutableCollections$List12: [Java, Kotlin] */
        LOGGER.info("{}: {}", twoLanguagesList.getClass(), twoLanguagesList);
    }

    @Test
    public void testListOfThreeElementsWithClass() {
        List<String> threeLanguagesList = List.of("Java", "Kotlin", "Scala");

        /* java.util.ImmutableCollections$ListN: [Java, Kotlin, Scala] */
        LOGGER.info("{}: {}", threeLanguagesList.getClass(), threeLanguagesList);
    }

    @Test
    public void testListOfNElementsWithClass() {
        List<Integer> fibSequencePart = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89);

        /* java.util.ImmutableCollections$ListN: [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89] */
        LOGGER.info("{}: {}", fibSequencePart.getClass(), fibSequencePart);
    }
}
