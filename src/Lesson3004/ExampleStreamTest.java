package Lesson3004;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExampleStreamTest {

    @Test
    void sum() {
        ExampleStream ex=new ExampleStream();
        int factResult=ex.sum(1,2,3);
        assertEquals(6,factResult);

    }
}