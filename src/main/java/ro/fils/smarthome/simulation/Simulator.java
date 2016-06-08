/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.simulation;

import ro.fils.smarthome.util.support.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.model.Item;
import ro.fils.smarthome.model.Node;
import ro.fils.smarthome.util.SensorLogger;
import ro.fils.smarthome.util.support.Activities_Type;
import ro.fils.smarthome.util.support.BoolWithLog;
import ro.fils.smarthome.tasks.TaskManager;

/**
 *
 * @author andre
 */
public class Simulator {

    SimulationMap map;
    Map<String, Double> recentlyTriggered;

    int simsPerSec;
    public Time time;
    public double currentTime;
    private final TaskManager taskManager;
    private SensorLogger sensorlogger;

    /**
     *
     * @param map
     * @param taskmanager
     * @param simulationsPerSec
     */
    public Simulator(SimulationMap map, TaskManager taskmanager, int simulationsPerSec) {
        this.map = map;
        this.sensorlogger = null;
        this.simsPerSec = simulationsPerSec;
        this.time = new Time(0);
        this.currentTime = 0;
        this.taskManager = taskmanager;
        this.recentlyTriggered = new HashMap<>();
    }

    public void setSensorLogger(SensorLogger sl) {
        this.sensorlogger = sl;
    }

    /**
     *
     * @return True if any player moved
     */
    public BoolWithLog simulationStep() {
        boolean movement = false;
        String log = null;
        for (Agent agent : map.getPeople()) {
            if (agent.getPauseTime() > 0.0) {
                agent.passTime(1.0 / simsPerSec);
                continue;
            }
            if (agent.isMoving()) {
                    movement = true;
                    agent.setCurrentLocation(map.moveActor(agent, simsPerSec));
            } else if (agent.getTargetItem() != null) {
                agent.progressFetch(1.0 / simsPerSec);
                Node current = map.getClosestNode(agent.getCurrentLocation());
                if (agent.getFetchTime() <= 0.0) {
                    Item fetchedItem = map.popItem(agent.getTargetItem(), current);
                    while (fetchedItem != null) {
                        agent.addItem(fetchedItem);
                        fetchedItem = map.popItem(fetchedItem.getName(), current);
                        if (fetchedItem == null) {
                            if (map.hasItem(agent.getTargetItem(), 1)) {
                                taskManager.moveForItems(agent, (agent.getGoalTask() != null ? agent.getGoalTask() : (agent.getCurrentTask() != null ? agent.getCurrentTask() : null)), map);
                            }
                        }
                    }
                    agent.setTargetItem(null);
                }
            } else if (agent.getCurrentTask() != null) {
                agent.progressTask(1.0 / simsPerSec);
                if (agent.getRemainingTaskDuration() <= 0.0) {
                    if (agent.getCurrentTask().getType().equals(Activities_Type.Automatic.toString())) {
                        map.addTask(agent.getCurrentTask(), map.getClosestNode(agent.getCurrentLocation()));
                        agent.getCurrentTask().getNeg().stream().forEach((stat) -> {
                            agent.addState("-" + stat);
                        });
                        agent.getCurrentTask().getPos().stream().forEach((stat) -> {
                            agent.addState("+" + stat);
                        });

                    } else {
                        agent.getCurrentTask().completeTask(agent, map);
                        agent.getCurrentTask().recentlyCompleted();
                    }
                    agent.setCurrentTask(null, null);
                }
            } else if (Math.random() < 0.15 && agent.getPauseTime() == 0.0) {
                try {
                    agent.setRoute(AStarImpl.getRoute(map.getRandomNode(), map.getClosestNode(agent.getCurrentLocation())));
                } catch (Exception ex) {
                    Logger.getLogger(Simulator.class.getName()).warning("No route found");
                }
            } else {
                log = taskManager.findTask(agent, map, currentTime);
            }
            sensorlogger.log(agent, map.getSensors(), currentTime);
            agent.passTime(1.0 / simsPerSec);

        }
        taskManager.passTime(1.0 / simsPerSec);
        map.progressTasks(1.0 / simsPerSec);
        currentTime += (1.0 / simsPerSec);
        return new BoolWithLog(movement, log);
    }
}
