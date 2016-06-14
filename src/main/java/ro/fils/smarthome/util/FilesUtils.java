/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.fils.smarthome.model.Agent;

/**
 *
 * @author Silvia
 */
public class FilesUtils {

    public static List<String> getAllLogsFromFolderForScenario(String scenarioName) {
        String userHomeFolder = System.getProperty("user.home") + "\\Desktop\\logs\\";
        List<String> paths = new ArrayList<>();
        try {
            Files.walk(Paths.get(userHomeFolder))
                    .forEach(filePath -> {
                        if (Files.isRegularFile(filePath)) {
                            System.out.println(filePath.toString() + " " +scenarioName);
                            if (filePath.toString().contains(scenarioName)) {
                                paths.add(filePath.toString());
                            }
                        }
                    });
        } catch (IOException ex) {
            Logger.getLogger(FilesUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paths;
    }

    public static Map<String, Double> getTraveledKmsForLogFile(String fileName, int scenarioId, String agentNameUI) {
        Map<String, Double> mapDays = buildMap();
        FileReader fr;
        String line;
        Point reference = new Point();
        try {
            fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split("-");
                String agentName = lineParts[7];
                if (agentName.equalsIgnoreCase(agentNameUI)) {
                    String day = lineParts[3];
                    String position = lineParts[9];
                    String[] pointXY = position.substring(1, position.length() - 1).split(",");
                    Point currentPoint = new Point((int) Double.parseDouble(pointXY[0]), (int) Double.parseDouble(pointXY[1]));
                    if (reference.getLocation() == null) {
                        reference.setLocation(Integer.parseInt(pointXY[0]), Integer.parseInt(pointXY[1]));
                    } else {
                        mapDays.put(day, mapDays.get(day) + Math.abs(reference.distance(currentPoint)));
                        reference = currentPoint;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilesUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mapDays;
    }

    private static Map<String, Double> buildMap() {
        Map<String, Double> map = new HashMap<>();
        map.put("Monday", 0d);
        map.put("Tuesday", 0d);
        map.put("Wednesday", 0d);
        map.put("Thursday", 0d);
        map.put("Friday", 0d);
        map.put("Saturday", 0d);
        map.put("Sunday", 0d);
        return map;
    }
}
