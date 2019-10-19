package org.avb.whatsnew.java12.superswitch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * In Java 12, the old switch statement was drastically improved.
 * As you remember, the old form of this statement looked old fashioned (not like in Kotlin/Scala :))
 * and, more importantly, it could lead to errors due to the lack of breaks.
 * Now, it supports multiple values in cases, allows you do not use breaks,
 * can be used an expression and has a nice arrow syntax (->) in addition to old colon (:).
 */
public class SwitchExpressionDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(SwitchExpressionDemo.class);

    /** Just to avoid some warnings in examples */
    private int getDayNumber() {
        return 6;
    }
    /**
     * Here is an old switch statement which exists from beginning.
     * Just to remember.
     */
    @Test
    public void testTraditionalSwitchStatement() {
        String dayOfWeekName;

        switch (getDayNumber()) {
            case 1:
                dayOfWeekName = "Monday";
                break;
            case 2:
                dayOfWeekName = "Tuesday";
                break;
            case 3:
                dayOfWeekName = "Wednesday";
                break;
            case 4:
                dayOfWeekName = "Thursday";
                break;
            case 5:
                dayOfWeekName = "Friday";
                break;
            case 6:
                dayOfWeekName = "Saturday";
                break;
            case 7:
                dayOfWeekName = "Sunday";
                break;
            default:
                dayOfWeekName = "An unknown day";
        }

        LOGGER.info(dayOfWeekName);
    }

    /**
     * Here is a new switch statement with the nice arrow syntax.
     * Now it looks more compact and less boilerplate.
     * It's hard to make a mistake forgetting breaks.
     */
    @Test
    public void testModernSwitchStatementWithArrowSyntax() {
        String dayOfWeekName;

        switch (getDayNumber()) {
            case 1 -> dayOfWeekName = "Monday";
            case 2 -> dayOfWeekName = "Tuesday";
            case 3 -> dayOfWeekName = "Wednesday";
            case 4 -> dayOfWeekName = "Thursday";
            case 5 -> dayOfWeekName = "Friday";
            case 6 -> dayOfWeekName = "Saturday";
            case 7 -> dayOfWeekName = "Sunday";
            default -> dayOfWeekName = "An unknown day";
        }

        LOGGER.info(dayOfWeekName);
    }

    /**
     * Here is a switch expression which allows you to return the result from cases
     * and avoid duplicate assignments. It looks fantastic!
     */
    @Test
    public void testSwitchExpressionWithArrowSyntax() {
        var dayOfWeekName = switch (getDayNumber()) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "An unknown day";
        };

        LOGGER.info(dayOfWeekName);
    }

    /**
     * Another interesting thing, that switch (both statement and expression)
     * supports multiple labels in cases.
     * See the calculate method below.
     */
    @Test
    public void testSwitchWithMultipleLabelsInCases() {
        assertEquals(calculate(3, 5, "+"), 8);
        assertEquals(calculate(4, 2, "plus"), 6);
        assertEquals(calculate(3, 3, "*"), 9);
        assertEquals(calculate(2, 3, "multiply"), 6);
        // and so on
    }

    private int calculate(int a, int b, String op) {
        return switch (op) {
            case "+", "plus" -> a + b;
            case "-", "minus" -> a - b;
            case "*", "mult", "multiply" -> a * b;
            default -> {
                String errorMsg = String.format("An unknown operation: %s", op);
                throw new IllegalArgumentException(errorMsg);
            }
        };
    }
}
