package org.example.task3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/java/org/example/task3/words.txt");

        String text = read(file);
        count(text);
//        countWithStream(text);
    }



    private static String read(File file) {
        StringBuilder result = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = "";
            while ((line = br.readLine()) != null){
                result.append(line);
                result.append(" ");
            }
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }


        return result.toString().replaceAll("[\\s]{2,}", " ");
    }

    public static void count(String text){
        String[] words = text.split(" ");

        Comparator<Map.Entry<String, Integer> > comparator = new Comparator<>() {

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };


        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words){
            if (!wordCount.containsKey(word)){
                wordCount.put(word, 0);
            }
            wordCount.put(word, wordCount.get(word) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCount.entrySet());

        Collections.sort(list, comparator.reversed());

        for (Map.Entry<String, Integer> item : list){
            System.out.println(item.getKey() + " " + item.getValue());
        }

    }

    private static void countWithStream(String text) {
        String[] words = text.split(" ");

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words){
            if (!wordCount.containsKey(word)){
                wordCount.put(word, 0);
            }
            wordCount.put(word, wordCount.get(word) + 1);
        }

        List<Map.Entry<String, Integer>> list = wordCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList();

        for (Map.Entry<String, Integer> item : list){
            System.out.println(item.getKey() + " " + item.getValue());
        }
    }
}
