/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author BadCatss
 */
public class Class1 {

    public static void main(String[] args) {

        //Task1
//Продемонструйте можливі способи створення Stream<String>, а саме –
//на підставі набору значень;
        var stream1 = Stream.of("a", "b", "c"); // Stream<String>
//на підставі масиву
        String[] array;
        array = new String[]{"a", "b", "c", "d", "e"};
        Arrays.stream(array); // Stream<String>
//на підставі колекції
        Map<String, String> map = new HashMap<>();
        map.put(" ", "linode.com");
        map.put(" ", "heroku.com");

        String result = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if ("something".equals(entry.getValue())) {
                result = entry.getValue();
            }
        }
//на підставі файлу
        List<String> lines = new ArrayList<>();
        Path path = Paths.get("file.txt");

        try ( BufferedReader reader = Files.newBufferedReader(path)) {

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException ignored) {
        }

        //System.out.println(lines); 
//згенерувати на підставі функції
        Stream.generate(new Random()::nextInt)
                .limit(5).forEach(System.out::println);

//Task2
        GenerateNumbers(0, 2, 10);
        PrintNumbers(numbers, 8);
//Task3
        String[] arr1 = new String[]{"1", "2", "3"};
        String[] arr2 = new String[]{"a", "b", "c"};

        MyMap<String> map1 = new MyMap<String>(arr1, arr2);
        if (map1.GetValues("1")!=null) {
            System.out.println(map1.GetValues("1"));
        }
        

    }
    static List<Integer> numbers;

    static void GenerateNumbers(int x, int y, int z) {
        numbers = new LinkedList<Integer>();
        Stream.iterate(x, n -> n + y).limit(z - 1).forEach(k -> numbers.add(k));
        System.out.println(numbers);

    }

    static void PrintNumbers(List<Integer> someNumbers, int equalNumber) {

        var d = someNumbers.stream().filter((p) -> p < equalNumber).collect(Collectors.summingInt(Integer::intValue));
        System.out.println(d.intValue());
    }
}

//Task3
class MyMap< E> {

    E[] keys;
    E[] values;

    public MyMap(E[] keys, E[] values) {
        this.keys = keys;
        this.values = values;
    }

    public void AddValue(E key, E Value) {

        final E[] keys = (E[]) new Object[this.keys.length + 1];
        for (int i = 0; i < this.keys.length; i++) {
            keys[i] = this.keys[i];
        }
        keys[keys.length - 1] = key;
        this.keys = keys;

        final E[] values = (E[]) new Object[this.values.length + 1];
        for (int i = 0; i < this.values.length; i++) {
            values[i] = this.values[i];
        }
        values[values.length - 1] = key;
        this.values = values;
    }

    public E GetValues(E key) {

        for (int i = 0; i < this.keys.length; i++) {
            if (keys[i].equals(key)) {

                return values[i];

            }
        }
        return null;
    }

}
