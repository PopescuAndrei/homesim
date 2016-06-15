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

    public static String getReferenceLogPathForScenario(String scenarioName) {
        List<String> allLogsFromFolderForScenario = getAllLogsFromFolderForScenario(scenarioName);
        for (String s : allLogsFromFolderForScenario) {
            if (s.contains("Reference")) {
                return s;
            }
        }
        return null;
    }

    public static Map<String, Double> getTraveledMetersForLogFile(String fileName, int scenarioId, String agentNameUI) {
        Map<String, Double> mapDays = buildTravelMetersMap();
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

    /**
     * Returns a map with the number of sensor readings if
     *
     * @param fileName
     * @param scenarioId
     * @param selectedAgent
     * @return
     */
    public static double getSensorReadings(String fileName, int scenarioId, String selectedAgent) {
        FileReader fr;
        String line;
        double sensorCount = 0;
        try {
            fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split("-");
                String agentName = lineParts[7];
                if (agentName.equalsIgnoreCase(selectedAgent)) {
                    sensorCount += 1;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilesUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sensorCount;
    }

    public static Map<String, Integer> getMovementStatistics(String fileName, String agentNameUI, String choosenDay) {
        Map<String, Integer> mapSensorsDetectingNoMovement = new HashMap<>();
        FileReader fr;
        String line;
        Point reference = new Point();
        String sensorRef = "";
        int time = 0;
        String timeRef = "-";
        try {
            fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split("-");
                String agentName = lineParts[7];

                if (agentName.equalsIgnoreCase(agentNameUI)) {
                    String day = lineParts[3];
                    if (choosenDay.equals(day)) {
                        String position = lineParts[9];
                        String currentSensor = lineParts[5];
                        String[] pointXY = position.substring(1, position.length() - 1).split(",");
                        Point currentPoint = new Point((int) Double.parseDouble(pointXY[0]), (int) Double.parseDouble(pointXY[1]));
                        if (reference.getLocation() == null) {
                            reference.setLocation(Integer.parseInt(pointXY[0]), Integer.parseInt(pointXY[1]));
                        } else {
                            double distance = Math.abs(reference.distance(currentPoint));
                            if (distance == 0.0 && currentSensor.equals(sensorRef)) {
                                timeRef = lineParts[15];
                            } else {
                                if (!timeRef.equals("-")) {
                                    String currentTime = lineParts[15];
                                    String[] splitCurrentTime = currentTime.split(":");
                                    String[] splitTimeRef = timeRef.split(":");

                                    time = (Integer.parseInt(splitCurrentTime[0]) - Integer.parseInt(splitTimeRef[0])) * 3600
                                            + (Integer.parseInt(splitCurrentTime[1]) - Integer.parseInt(splitTimeRef[1])) * 60
                                            + (Integer.parseInt(splitCurrentTime[2]) - Integer.parseInt(splitTimeRef[2]));
                                }

                                if (time != 0) {
                                    if (mapSensorsDetectingNoMovement.get(sensorRef) != null) {
                                        time += mapSensorsDetectingNoMovement.get(sensorRef);
                                    }
                                    mapSensorsDetectingNoMovement.put(sensorRef, time);
                                }
                              
                            }
                            sensorRef = currentSensor;
                            reference = currentPoint;
                            time = 0;
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilesUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mapSensorsDetectingNoMovement;
    }

    private static Map<String, Double> buildTravelMetersMap() {
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
