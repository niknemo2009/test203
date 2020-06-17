package lab7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zavd2 {
    public static void main(String[] args) {
        Stream<String> firstMethod = Stream.of("One", "Two", "Three");
        firstMethod.forEach(System.out::println);

        String[] arrOfWords = {"Four", "Five", "Six"};
        Stream<String> secondMethod = Arrays.stream(arrOfWords);
        secondMethod.forEach(System.out::println);

        List<String> listOfWords = new ArrayList<>();
        listOfWords.add("Seven");
        listOfWords.add("Eight");
        Stream<String> thirdMetod = listOfWords.stream();
        thirdMetod.forEach(System.out::println);

        Path path = Paths.get("file.txt");
        List<String> linesList = new ArrayList<>();
        try(Stream<String> fourthMethod = Files.newBufferedReader(path).lines())
        {
            linesList = fourthMethod.collect(Collectors.toList());
        }
        catch (IOException ex){}
        System.out.println(linesList);

        String[] arr = {"Eleven", "Twelve"};
        Zavd2 z = new Zavd2();
        z.createStream(arr);
    }
    private <T> void createStream(T[] arr)
    {
        Stream<T> arraysStream = Arrays.stream(arr);
        arraysStream.forEach(System.out::println);
    }
}
