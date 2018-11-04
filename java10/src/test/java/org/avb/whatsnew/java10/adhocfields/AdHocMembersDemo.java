package org.avb.whatsnew.java10.adhocfields;

import org.avb.whatsnew.java10.typeinference.LocalVariableTypeInferenceDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Since Java 10 it is possible to expand a class with new fields and methods
 * using anonymous classes together with var.
 *
 * That is not a good practice, but may be used for a reason
 */
public class AdHocMembersDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdHocMembersDemo.class);

    @Test
    public void testAdHocMembers() {
        var intPair = new Object() {
            int first = 0;
            int second = 0;

            public int sum() {
                return first + second;
            }
        };

        intPair.first = 10;
        intPair.second = 20;

        LOGGER.info("sum of {} and {} is {}", intPair.first, intPair.second, intPair.sum());
    }
}