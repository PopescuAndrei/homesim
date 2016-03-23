/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.model.Item;
import ro.fils.smarthome.model.Node;
import ro.fils.smarthome.model.Time;
import ro.fils.smarthome.util.SensorLogger;
import ro.fils.smarthome.astar.AStarImpl;

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
     * @param simulationsPerSec
     */
    public Simulator(SimulationMap map, TaskManager taskmanager, int simulationsPerSec){
        this.map = map;
        this.sensorlogger = null;
        this.simsPerSec = simulationsPerSec;
        this.time = new Time(0);
        this.currentTime = 0;
        this.taskManager = taskmanager;
        this.recentlyTriggered = new HashMap<>();
    }
    
    public void setSensorLogger(SensorLogger sl){
        this.sensorlogger = sl;
    }
    
    /**
     * 
     * @return True if any player moved
     */
    public boolean simulationStep()
    {
        boolean movement = false;
        for(Agent agent: map.getPeople())
        {
            if(agent.getPauseTime() > 0.0){
                agent.passTime(1.0/simsPerSec);
                continue;
            }
            if(agent.isMoving())
            {   //We're traveling!
                if(agent.getType() == 0){ 
                    movement = true;
                    agent.setLocation(map.moveActor(agent, simsPerSec));
                }else{
                    agent.setLocation(map.moveActor(agent, simsPerSec * 6));
                }
            }
            else if(agent.getType() == 0 && agent.getTargetItem() != null)
            {
                agent.progressFetch(1.0/simsPerSec);
                Node current = map.getClosestNode(agent.currentLocation());
                if(agent.getFetchTime() <= 0.0){
                    Item fetchedItem = map.popItem(agent.getTargetItem(), current);
                    while(fetchedItem != null){ 
                        agent.addItem(fetchedItem);
                        fetchedItem = map.popItem(fetchedItem.getName(), current);
                        if(fetchedItem == null){
                            if(map.hasItem(agent.getTargetItem(), 1)){
                                taskManager.moveForItems(agent, (agent.getGoalTask() != null?agent.getGoalTask():(agent.getCurrentTask() != null?agent.getCurrentTask():null)), map);
                            }
                        }
                    }agent.setTargetItem(null);
                }
            }
            else if(agent.getType() == 0 && agent.getCurrentTask() != null)
            {
                agent.progressTask(1.0/simsPerSec);
                if(agent.remainingDuration() <= 0.0){
                    if(agent.getCurrentTask().getType().equals("Automatic")){
                        map.addTask(agent.getCurrentTask(), map.getClosestNode(agent.currentLocation()));
                        for(String stat: agent.getCurrentTask().getNeg()){
                            agent.addState("-" + stat);
                        }for(String stat: agent.getCurrentTask().getPos()){
                            agent.addState("+" + stat);
                        }
                        
                    }else{
                        agent.getCurrentTask().completeTask(agent, map);
                        agent.getCurrentTask().recentlyCompleted();
                    }
                    agent.setCurrentTask(null, null);
                }
            }
            else{
                if(Math.random() < 0.15 && agent.getPauseTime() == 0.0)
                {
                    try {
                        agent.setRoute(AStarImpl.getRoute(map.getRandomNode(), map.getClosestNode(agent.currentLocation())));
                    } catch (Exception ex) {
                        Logger.getLogger(Simulator.class.getName()).warning("No route found");
                    }
                }else if(agent.getType() == 0){
                    taskManager.findTask(agent, map, currentTime);
                }
            }
            sensorlogger.log(agent, map.getSensors(), currentTime);
            agent.passTime(1.0/simsPerSec);
            
        }
        taskManager.passTime(1.0/simsPerSec);
        map.progressTasks(1.0/simsPerSec);
        currentTime += (1.0/simsPerSec);
        return movement;
    }
}