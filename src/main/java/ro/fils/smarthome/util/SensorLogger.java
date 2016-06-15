/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.tasks.ITask;
import ro.fils.smarthome.sensors.Camera;
import ro.fils.smarthome.sensors.Sensor;
import ro.fils.smarthome.util.support.Time;

/**
 *
 * @author andre
 */
public class SensorLogger {

    private static final Logger LOG = Logger.getLogger(SensorLogger.class.getName());

    private final BufferedWriter fileWriter;
    private final DecimalFormat df;
    private final Map<String, Double> lastTriggered;
    private Set<String> lastPoses;
    private ITask lastTask;

    private final double TRIGGER_INTERVAL = 5.0;

    public SensorLogger(String fileName) throws IOException {
        String userHomeFolder = System.getProperty("user.home") + "\\Desktop\\logs\\";
        this.fileWriter = new BufferedWriter(new FileWriter(new File(userHomeFolder, fileName), true));
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df = new DecimalFormat("##.#####", dfs);
        df.setMinimumIntegerDigits(2);
        df.setMaximumIntegerDigits(2);
        df.setMinimumFractionDigits(6);
        df.setMaximumFractionDigits(6);
        lastTriggered = new HashMap<>();
        lastPoses = null;
        lastTask = null;
    }

    public void log(Agent agent, Collection<Sensor> sensors, double currentTime, String day, String weekNumber) {

        sensors.stream().forEach((s) -> {
            s.getSensorAreas().stream().forEach((sa) -> {
                 if (sa.getArea() != null && sa.getArea().contains(agent.getCurrentLocation())) {
                    if (s instanceof Camera) {
                        Set<String> poses = agent.getPoseData();
                        if (lastPoses != poses && lastTask != agent.getCurrentTask()) {
                            lastPoses = poses;
                            lastTask = agent.getCurrentTask();
                            for (String pose : poses) {
                                if (!lastTriggered.containsKey(sa.getName() + pose)
                                        || (lastTriggered.get(sa.getName() + pose) != null
                                        && currentTime - lastTriggered.get(sa.getName() + pose) >= 10.0)) {
                                    lastTriggered.put(sa.getName() + pose, currentTime);
                                    logSensorReading(agent, s, currentTime, day, weekNumber);
                                }
                            }
                        }
                    }
                    if (!lastTriggered.containsKey(sa.getName())
                            || (lastTriggered.get(sa.getName()) != null && currentTime - lastTriggered.get(sa.getName()) >= TRIGGER_INTERVAL)) {
                        lastTriggered.put(sa.getName(), currentTime);
                        if (!sa.getLastValue().equals("ON")) {
                            logSensorReading(agent, s, currentTime, day, weekNumber);
                            sa.setLastValue("ON");
                        }
                    }
                } 
            });
        });
    }

    public void logSensorReading(Agent agent, Sensor sensor, double currentTime, String day, String weekNumber) {
        try {
            fileWriter.append("Week Number : -" + weekNumber + "- Day: -" + day +"- Sensor -" + sensor.getName() + "-: -"
                    + agent.getName() + "- is in position -("
                    + agent.getCurrentLocation().getX() + ", " + agent.getCurrentLocation().getY() + ")-, -"
                    + (sensor.getPosition() != null ? (int) sensor.getPosition().distance(agent.getCurrentLocation())
                            + "- distance from the sensor" : "") + ", doing -" + (agent.getGoalTask() == null ? "nothing-" : agent.getGoalTask().getName() + "-") 
                               + ", time -"+Time.getHours(currentTime)+":"+Time.getMinutes(currentTime)+":"+Time.getSeconds(currentTime)+"-"+ "\n");
            fileWriter.flush();
        } catch (IOException e) {

        }
    }
}
