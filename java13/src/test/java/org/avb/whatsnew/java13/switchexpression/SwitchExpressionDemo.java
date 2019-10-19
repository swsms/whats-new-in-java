package org.avb.whatsnew.java13.switchexpression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * In Java 13, switch expression can use the yield keyword
 * to return values from multi-statement cases.
 * Breaks with values are not allowed now.
 */
public class SwitchExpressionDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(SwitchExpressionDemo.class);

    @Test
    public void testSwitchExpressionWithYield() {
        var a = 10;
        var b = 20;
        var op = "+";

        var result = switch (op) {
            case "+" -> {
                var sum = a + b;
                LOGGER.info("Performing {} + {} ...", a, b);
                yield sum;
            }
            case "-" -> {
                var diff = a - b;
                LOGGER.info("Performing {} - {} ...", a, b);
                yield diff;
            }
            case "*" -> {
                var product = a * b;
                LOGGER.info("Performing {} * {} ...", a, b);
                yield  product;
            }
            default -> {
                String errorMsg = String.format("An unknown operation: %s", op);
                throw new IllegalArgumentException(errorMsg);
            }
        };

        LOGGER.info("The result is {}", result);
    }
}
