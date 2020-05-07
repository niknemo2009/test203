package Lesson070520;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Example1Test {

    @Test
    void maxLetterGolosn() {
        Example1 ex1=new Example1();
        String[]  arr={"qws","wsx","qsbnm"};
        String[]  arr1={};
        String[]  arr2={"qwes","wsax","qsybnm"};
        String[]  arr3={"qweyus","wisax","qsaybnm"};

        List<String> expResult=new ArrayList<>();
        List<String> expResult1=new ArrayList<>();
        List<String> expResult2=new ArrayList<>();
        expResult2.add("qwes");
        expResult2.add("wsax");
        expResult2.add("qsybnm");
       // List<String> expResult3=ex1.maxLetterGolosn(arr3);


        assertAll(
                ()->assertIterableEquals(new ArrayList<>(),ex1.maxLetterGolosn(arr)),
                ()->assertIterableEquals(new ArrayList<>(),ex1.maxLetterGolosn(arr1)),
                ()->assertIterableEquals(new ArrayList<>(),ex1.maxLetterGolosn(arr2))


        );



    }
}