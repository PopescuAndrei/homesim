/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author Silvia
 */
public class FilesUtils {

    public void readAllTxtFilesFromFolder() throws IOException {
        String userHomeFolder = System.getProperty("user.home") + "\\Desktop\\logs\\";
        Files.walk(Paths.get(userHomeFolder)).forEach(filePath -> {
            if (Files.isRegularFile(filePath) && filePath.getFileName().toString().endsWith(".txt")) {
                // just for testing => we should add some extra logic
//                readFile(userHomeFolder + "\\" + filePath.getFileName().toString());
            }
        });
    }

    public void readFile(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(System.out::println);
            // Do some further logic here
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parseLine(String line){
        String[] lineParts = line.split("-");
        for(int i = 0;i <lineParts.length; i++){
            System.out.println(lineParts[i] + " at index " + i);
        }
        String sensorName = lineParts[1];
        String agentName = lineParts[3];
        String position = lineParts[5];
        String distance = lineParts[7];
        String goal = lineParts[9];
        System.out.println(sensorName + " " + agentName + " " + position + " " + distance + " " + goal);
    }
}
