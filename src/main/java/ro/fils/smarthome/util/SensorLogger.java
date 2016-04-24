/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util;

import java.io.BufferedWriter;
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
import ro.fils.smarthome.tasksManagement.ITask;
import ro.fils.smarthome.sensor.Camera;
import ro.fils.smarthome.sensor.Sensor;

/**
 *
 * @author andre
 */
public class SensorLogger {

    private static final Logger LOG = Logger.getLogger(SensorLogger.class.getName());

    private final BufferedWriter fileWriter;
    private final DecimalFormat df;
    private final int suffix;
    private final Map<String, Double> lastTriggered;
    private Set<String> lastPoses;
    private ITask lastTask;

    private final double TRIGGER_INTERVAL = 5.0;

    public SensorLogger(String fileName) throws IOException {
        this.suffix = 1;
        this.fileWriter = new BufferedWriter(new FileWriter(fileName + suffix, true));
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

    public void log(Agent agent, Collection<Sensor> sensors, double currentTime) {

        sensors.stream().forEach((s) -> {
            s.getSensorAreas().stream().forEach((sa) -> {
                if (sa.getArea() == null && agent.getApplianceInUse() != null
                        && !agent.isMoving() && agent.getCurrentTask() != null
                        && sa.getName().contains(agent.getApplianceInUse().getType())
                        && (!lastTriggered.containsKey(agent.getApplianceInUse().getName())
                        || (lastTriggered.get(agent.getApplianceInUse().getName()) != null && currentTime - lastTriggered.get(agent.getApplianceInUse().getName()) >= 60.0))) {
                    lastTriggered.put(agent.getApplianceInUse().getName(), currentTime);
                    if (!sa.getLastValue().equals("ON")) {
                        try {
                            addSensor(agent, s,currentTime, agent.getApplianceInUse().getName() + "CT",
                                    agent.getApplianceInUse().getName() + "CT", "ON", (agent.getCurrentTask() != null
                                            ? (agent.getCurrentTask().label() != null ? agent.getCurrentTask().label()
                                                    : (agent.getGoalTask() != null
                                                            ? (agent.getGoalTask().label() != null ? agent.getGoalTask().label() : "Other_Activity")
                                                            : "Other_Activity"))
                                            : "Other_Activity"));
                            sa.setLastValue("ON");
                        } catch (IOException ex) {
                            LOG.log(Level.SEVERE, null, ex);
                        }
                    }
                } else if (sa.getArea() != null && sa.getArea().contains(agent.getCurrentLocation())) {
                    if (s instanceof Camera && !agent.isMoving()) {
                        Set<String> poses = agent.getPoseData();
                        if (lastPoses != poses && lastTask != agent.getCurrentTask()) {
                            lastPoses = poses;
                            lastTask = agent.getCurrentTask();
                            for (String pose : poses) {
                                if (!lastTriggered.containsKey(sa.getName() + pose)
                                        || (lastTriggered.get(sa.getName() + pose) != null
                                        && currentTime - lastTriggered.get(sa.getName() + pose) >= 10.0)) {
                                    lastTriggered.put(sa.getName() + pose, currentTime);
                                    try {
                                        addSensor(agent, s, currentTime, sa.getName(), sa.getName(), pose,
                                                (agent.getCurrentTask() != null
                                                        ? (agent.getCurrentTask().label() != null ? agent.getCurrentTask().label()
                                                                : (agent.getGoalTask() != null
                                                                        ? (agent.getGoalTask().label() != null ? agent.getGoalTask().label() : "Other_Activity")
                                                                        : "Other_Activity"))
                                                        : "Other_Activity"));
                                    } catch (IOException ex) {
                                        LOG.log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    }
                    if (!lastTriggered.containsKey(sa.getName())
                            || (lastTriggered.get(sa.getName()) != null && currentTime - lastTriggered.get(sa.getName()) >= TRIGGER_INTERVAL)) {
                        lastTriggered.put(sa.getName(), currentTime);
                        if (!sa.getLastValue().equals("ON")) {
                            try {
                                addSensor(agent, s, currentTime, sa.getName(),
                                        sa.getName(), "ON",
                                        (agent.getCurrentTask() != null
                                                ? (agent.getCurrentTask().label() != null ? agent.getCurrentTask().label()
                                                        : (agent.getGoalTask() != null
                                                                ? (agent.getGoalTask().label() != null ? agent.getGoalTask().label() : "Other_Activity")
                                                                : "Other_Activity"))
                                                : "Other_Activity"));
                                sa.setLastValue("ON");
                            } catch (IOException ex) {
                                LOG.log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else
                {
                    if (lastTriggered.containsKey(sa.getName()) && currentTime - lastTriggered.get(sa.getName()) >= TRIGGER_INTERVAL) {
                        if (!sa.getLastValue().equals("OFF")) {
                            try {
                                sa.setLastValue("OFF");
                            } catch (Exception ex) {
                                LOG.log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            });
        });
    }

    public void addSensor(Agent agent, Sensor sensor,double time, String sensorName, String sensorNote,
            String sensorValue, String activityName) throws IOException {

        int day = Time.getDay(time);
        int dayOfMonth = day % 30;
        int month = day / 30;
        int currentMonth = month % 11 + 1;
        int year = 2016 + (month / 12);
        fileWriter.append(year + "-" + Time.getNumberFormatted(currentMonth) + "-"
                + Time.getNumberFormatted(dayOfMonth) + " "
                + Time.getNumberFormatted(Time.getHours(time)) + ":"
                + Time.getNumberFormatted(Time.getMinutes(time)) + ":"
                + df.format(time % 60)
                + " " + removeSpaces(sensorName) + " " + removeSpaces(sensorNote)
                + " " + removeSpaces(sensorValue) + " "
                + removeSpaces(activityName) + "\n");
        fileWriter.flush();
    }

    public String removeSpaces(String text) {
        return text.replace(' ', '_');
    }

}
