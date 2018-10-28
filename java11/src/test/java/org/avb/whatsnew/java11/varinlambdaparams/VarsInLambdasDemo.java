package org.avb.whatsnew.java11.varinlambdaparams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.annotation.*;
import java.util.List;

/**
 * Java 11 introduces Local-Variable syntax for lambda parameters.
 * It is used for one specific case like (@Annotation var param) -> ...
 */
public class VarsInLambdasDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(VarsInLambdasDemo.class);

    @Test
    public void testVarInLambdas() {
        List.of("Java", "Kotlin", "Scala", "Closure").stream()
                .filter((@NotNull var lang) -> lang.startsWith("J"))
                .forEach(LOGGER::info); // prints "Java"
    }
}

@Target(ElementType.PARAMETER)
@Inherited
@Retention(RetentionPolicy.CLASS)
@interface NotNull { }