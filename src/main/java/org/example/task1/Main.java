package org.example.task1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String pattern1 = "([(]\\d{3}[)])([ ])(\\d{3})([-])(\\d{4})";
    public static final String pattern2 = "(\\d{3})([-])(\\d{3})([-])(\\d{4})";

    public static void main(String[] args) {

        File file = new File("src/main/java/org/example/task1/file.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null){
                if (line.matches(pattern1) || line.matches(pattern2)) {
                    System.out.println(line);
                }
                line = bufferedReader.readLine();
            }
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }


    }
}
