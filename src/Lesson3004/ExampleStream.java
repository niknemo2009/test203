package Lesson3004;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;

public class ExampleStream {

    public static void main(String[] args) {
//List<Integer> list=new ArrayList<>();
//list.add(3);
//list.add(7);
//list.add(5);
//list.add(2);
//list.add(5);
//list.add(90);
//list.stream().forEach(new One());
//list.stream().forEach(new Consumer<Integer>() {
//    @Override
//    public void accept(Integer integer) {
//        System.out.println(integer);
//    }
//});
//        list.stream().forEach(s-> System.out.println(s));
//        list.stream().forEach(System.out::println);
//
//        list.stream().forEach(ExampleStream::metodStatic);
//        ExampleStream ex=new ExampleStream();
//        list.stream().forEach(ex::metod33);
int[] array={12,25,3455,41,53,61,72};
       Arrays.stream(array).mapToObj( String::valueOf).forEach(ExampleStream::sumDigit);
Arrays.stream(array).forEach(ExampleStream::otherMetod);
           //public String apply(int value) {


    }

    private static void otherMetod(int i) {
//        int temp=number/10;
//        int result=number%10;

//        while(temp>0){
//            temp=temp/10;
//            result+=temp%10;
//        }
//        System.out.println(result);
//        {
//            int res = 0;
//            while(i >= 1)
//            {
//                res += i % 10;
//                i /= 10;
//            }
//            System.out.println(res);
//        }
        int result=0;
        for (;i!=0;){result += i%10;i /=10;}
        System.out.println(result);
    }

    private static void sumDigit(String q) {
        int result=0;
        for(String digit:q.split("")){
            result+=Integer.valueOf(digit);
        }
        System.out.println(result);
    }

    void metod(int element){
    System.out.println(element);
}
   static  void metodStatic(int element){
        System.out.println(element);
    }
    int metod33(int element){
        System.out.println(element);
    return  45;
    }
    int sum(int ... elements){
        int result=0;
        for(int temp:elements){
result+=temp;
        }
        return result;
    }
}

class One implements Consumer<Integer>{

    @Override
    public void accept(Integer integer) {
        System.out.println(integer);
    }
}