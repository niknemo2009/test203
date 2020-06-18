package lab6;

import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

public class GoalsTest {

    @ParameterizedTest
    @DisplayName("value source test")
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        Assert.assertTrue(Numbers.isOdd(number));
    }

    @ParameterizedTest
    @DisplayName("Csv source test")
    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
    void toUpperCase(String input, String expected) {
        String actualValue = input.toUpperCase();
        Assert.assertEquals(expected, actualValue);
    }

    @Test
    @DisplayName("Adding an action test")
    public void testAdding()
    {
        Goals g = new Goals("g1");
        Actions a11 = new Actions("labi", 1, LocalDate.of(2002, 7, 12));
        g.add_action(a11);
        int check = g.getAct().size();
        Assert.assertEquals(1, check);
    }

    @Test
    @DisplayName("Remove an action test")
    public void testRemove()
    {
        Goals g = new Goals("g1");
        Actions a11 = new Actions("labi", 1, LocalDate.of(2002, 7, 12));
        Actions a12 = new Actions("labi", 1, LocalDate.of(2002, 7, 12));
        Actions a13 = new Actions("labi", 1, LocalDate.of(2002, 7, 12));
        g.add_action(a11, a12, a13);
        g.remove_action(a13);
        int check = g.getAct().size();
        Assert.assertEquals(2, check);
    }

    @Test
    @DisplayName("Count goal time test")
    void testCount()
    {
        Goals g = new Goals("g1");
        Actions a11 = new Actions("labi", 1, LocalDate.of(2002, 7, 12));
        Actions a12 = new Actions("labi", 1, LocalDate.of(2002, 7, 12));
        g.add_action(a11, a12);
        int sum = g.count_time();
        Assert.assertEquals(2, sum);
    }
}
