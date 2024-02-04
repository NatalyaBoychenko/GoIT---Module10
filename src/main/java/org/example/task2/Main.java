package org.example.task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File inFile = new File("src/main/java/org/example/task2/file.txt");
        File outFle = new File("src/main/java/org/example/task2/user.json");

        read(inFile);
        write(inFile, outFle);

    }

    public static List<User> read(File file){
        List<User> userList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            String firstLine = br.readLine();
            String line = "";
            while ((line = br.readLine()) != null){
                String[] value = line.split(" ");
                userList.add(new User(value[0], Integer.parseInt(value[1])));
            }
        } catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return userList;
    }

    public static void write(File inFile, File outFile){
        List<User> userList = read(inFile);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userList);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
            bw.write(json);
        } catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}
