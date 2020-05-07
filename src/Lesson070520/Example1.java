package Lesson070520;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Example1 {
    public static void main(String[] args) {
Predicate<String> lam1= w->w.length()>5;
Consumer<String>  lam2=q->{
            System.out.println(q);
        };
        Function<String,String>  lam3=(w)->w.substring(0);
        Supplier<String>  lam4=()->"qwqwqwq";

        String[] arr={"qazerty","eerty","edrfyuio"};
        Example1 ex1=new Example1();
        System.out.println(ex1.maxLetterGolosn(arr));
        System.out.println( "qwe".indexOf("q"));
    }

    List<String> maxLetterGolosn(String[] arr){
        List<String> result=new ArrayList<>();
        String etalon="eyuioa";
        int[] calcLetter=Arrays.stream(arr).mapToInt(w->{
            int counter=0;
            for(String temp:w.split("")){
                if(etalon.indexOf(temp)!=-1){
                    counter++;
                }
            }
            return counter;
        }).toArray();
        int[] mass=Arrays.stream(calcLetter).distinct().sorted().toArray();
        if(mass[0]!=0&& mass.length>1){
            List<Integer>  list=findAllIndex(calcLetter);
            for (int temp:list ) {
                result.add(arr[temp]);
            }

        }
        return result;
    }

    private List<Integer> findAllIndex(int[] mass) {
        List<Integer> result=new ArrayList<>();
        int max=Arrays.stream(mass).max().getAsInt();
        for(int i=0;i<mass.length;i++){
            if(mass[i]==max){
                result.add(i);
            }
        }
        return result;
    }
}
