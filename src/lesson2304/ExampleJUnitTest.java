package lesson2304;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ExampleJUnitTest {

    @ParameterizedTest
    @MethodSource("sourceOne")
    void oneeerere(int[] array,int index,int[] expectResult ) {
        ExampleJUnit ex=new ExampleJUnit();
        int[] arr={1,2,3,4,5,8,9};
        int[] factResult=ex.deleteElement(array,index);
        assertArrayEquals(expectResult,factResult);
    }
    static Stream<Arguments> sourceOne(){
      return Stream.of(
          Arguments.arguments(new int[]{1,2,3},2,new int[]{1,2}),
          Arguments.arguments(new int[]{1,2,3},5,new int[]{1,2,3}),
          Arguments.arguments(new int[]{},5,new int[]{}),
          Arguments.arguments(new int[]{},0,new int[]{})
      )  ;
    }

    @ParameterizedTest
    @MethodSource("sourceOne")
    void onee(int[] array,int index,int[] expectResult) {
        ExampleJUnit ex=new ExampleJUnit();
        int[] arr={1,2,3,4,5,8,9};
        int[] factResult=ex.deleteElementwithEgor(array,index);
        assertArrayEquals(expectResult,factResult);
    }

}