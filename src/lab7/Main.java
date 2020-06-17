package lab7;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = new Integer[50002];
        for(int i = 0; i < arr.length; i++)
        {
            arr[i] = i;
        }

        Stream<Integer> streamOfNumbers = Arrays.stream(arr);
        Integer[] filtered = streamOfNumbers.filter(x -> x%10%3 == 0).toArray(Integer[]::new);

        String[] arr2 = new String[filtered.length];
        for(int i = 0; i < arr2.length; i++)
        {
            arr2[i] = String.valueOf(filtered[i]);
        }

        Stream<String> converted = Arrays.stream(arr2);
        converted.forEach(System.out::println);
    }
}
