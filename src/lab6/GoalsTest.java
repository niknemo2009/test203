package lab6;

import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

public class GoalsTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        Assert.assertTrue(Numbers.isOdd(number));
    }

    @Test
    public void testAdding()
    {
        Goals g = new Goals("g1");
        Actions a11 = new Actions("labi", 1, LocalDate.of(2002, 7, 12));
        g.add_action(a11);
        int check = g.getAct().size();
        Assert.assertEquals(1, check);
    }

    @Test
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
