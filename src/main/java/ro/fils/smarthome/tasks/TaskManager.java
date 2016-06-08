/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.fils.smarthome.simulation.AStarImpl;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.model.Appliance;
import ro.fils.smarthome.model.Need;
import ro.fils.smarthome.model.Node;
import ro.fils.smarthome.util.PPlanWrapper;
import ro.fils.smarthome.simulation.SimulationMap;
import ro.fils.smarthome.model.Item;

/**
 *
 * @author Silvia
 */
public class TaskManager {

    private static final Logger LOG = Logger.getLogger(TaskManager.class.getName());

    private Collection<ITask> tasks;

    public TaskManager(TaskReader j) {
        tasks = j.getTasks();

    }

    public String findTask(Agent person, SimulationMap map, double time) {
        String log = null;
        Collection<ITask> availableTasks = new ArrayList<>(filterAvailable(tasks, time));
        if (person.getGoalTask() != null && !person.getGoalTask().available(time)) {
            person.setGoalTask(null);
        }
        if (person.getGoalTask() != null) {
            if (person.getGoalTask().agentMeetsRequirements(person)) {
                LOG.log(Level.INFO, "Requirements met for task {0}", person.getGoalTask().getName());
                log = setTaskForAgent(person, person.getGoalTask(), map);
                person.setGoalTask(null);
            } else if (person.getGoalTask().itemExists(person, map)) {
                moveForItems(person, person.getGoalTask(), map);
            } else {
                try {
                    Deque<ITask> tusks = PPlanWrapper.getPlan(map, person, availableTasks, person.getGoalTask());
                    LOG.log(Level.INFO, "######## + {0}", tusks.toString());
                    log = setTaskForAgent(person, tusks.getFirst(), map);
                } catch (Exception ex) {
                    ArrayList<String> mapItems = new ArrayList<>();
                    for (Item it : map.getItems()) {
                        mapItems.add(it.getName());
                    }
                    LOG.log(Level.SEVERE, "Crashed when finding plan and setting task " + person.getGoalTask().getName()
                            + ", " + person.getGoalTask().getRequiredItemsSet() + ", " + person.getInventory().keySet() + ", "
                            + mapItems, ex);
                }
            }
        } else {
            LOG.info("Finding a new goal task");
            List<Need> needs = new ArrayList<>(person.getNeeds());
            Collection<ITask> scheduledTasks = getScheduled(availableTasks);
            Collection<ITask> tasksToRemoveStuff = getRemoverTasks(availableTasks, person, map);
            if (scheduledTasks != null) {
                person.setGoalTask(scheduledTasks.iterator().next());
            } else if (tasksToRemoveStuff != null) {
                person.setGoalTask(tasksToRemoveStuff.iterator().next());
            } else {
                while (!needs.isEmpty()) {
                    Need lowest = getLowestNeed(needs);

                    LOG.log(Level.FINE, "{0} is lowest need", lowest.getName());
                    ITask taskForNeed = taskForNeed(lowest, availableTasks, time, person, map);
                    if (taskForNeed != null) {
                        person.setGoalTask(taskForNeed);
                        break;
                    } else {
                        needs.remove(lowest);
                    }

                }
            }
        }
        return log;
    }

    public void moveForItems(Agent p, ITask task, SimulationMap map){
        for(String str: task.getRequiredItemsSet()){
            if(!p.hasItem(str, task.getRequiredItems().get(str)) && map.hasItem(str, task.getRequiredItems().get(str))){
                p.setTargetItem(str);
                LOG.info(str);
                try {
                    p.setRoute(AStarImpl.getRoute(map.getLocationsOfItem(str), map.getClosestNode(p.getCurrentLocation())));
                    if(map.getClosestNode(p.getCurrentLocation()).equals(p.getRoute().getLast())) p.setRoute(null);
                } catch (Exception ex) {
                    Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String setTaskForAgent(Agent agent, ITask task, SimulationMap map) {
        String log = null;
        if (task.itemExists(agent, map)) {
            moveForItems(agent, task, map);
        } else {
            log = String.format("%s is doing task %s for %s", agent.getName(),task.toString(),agent.getGoalTask().toString());
            try {
                Collection<Appliance> apps = map.getAppliances();
                Collection<String> valids = task.getUsedAppliances();
                Set<Appliance> validApps = new HashSet<>();
                for (Appliance app : apps) {
                    for (String valid : valids) {
                        if (app.getType().contains(valid)) {
                            validApps.add(app);
                        }
                    }
                }
                Collection<Node> goals = map.getLocationOfAppliances(valids);
                for (Agent a : map.getPeople()) {
                    if (!a.equals(agent) && a.getRoute() != null) {
                        goals.remove(a.getRoute().getLast());
                    }
                }
                Node closestNode = map.getClosestNode(agent.getCurrentLocation());
                agent.setRoute(AStarImpl.getRoute(goals, closestNode));
                for (Appliance app : validApps) {
                    if (app.getNode().equals(agent.getRoute().getLast())) {
                        agent.setCurrentTask(task, app);
                        task.consumeItem(agent);
                        break;
                    }
                }
                if (closestNode.equals(agent.getRoute().getLast())) {
                    agent.setRoute(null);
                }
            } catch (Exception ex) {
                LOG.severe("Error while setting task. Route not found! Maybe " + task.getUsedAppliances().toString() + " does not exist or appliance is in use");
                agent.setPauseTime(1000);
            }
        }
        return log;
    }

    public ITask taskForNeed(Need need, Collection<ITask> tasks, double time, Agent agent, SimulationMap map) {
        List<ITask> tasksForNeed = new ArrayList<>();
        for (ITask task : tasks) {
            if (task.fulfilledNeed() != null && task.fulfilledNeed().equals(need.getName())) {
                try {
                    PPlanWrapper.getPlan(map, agent, tasks, task);
                    tasksForNeed.add(task);
                } catch (Exception e) {
                    e.printStackTrace();
                    LOG.info(task.getName() + " has no viable plan");
                }
            }
        }
        LOG.info(tasksForNeed.size() + " possible tasks to fulfill " + need.getName());
        return (tasksForNeed.isEmpty() ? null : tasksForNeed.get(new Random().nextInt(tasksForNeed.size())));
    }

    public Need getLowestNeed(List<Need> needs) {
        double value = 1000.0;
        Need returnedNeed = null;
        for (Need need : needs) {
            if (value > need.getValue()) {
                value = need.getValue();
                returnedNeed = need;
            }
        }
        return returnedNeed;
    }

    public Collection<ITask> filterAvailable(Collection<ITask> allTasks, double time) {
        Collection<ITask> filteredTasks = new ArrayList<>();
        for (ITask task : allTasks) {
            if (task.available(time)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    private Collection<ITask> getScheduled(Collection<ITask> availableTasks) {
        Collection<ITask> schedules = new ArrayList<>();
        for (ITask t : availableTasks) {
            if (t.getType().equals("Scheduled")) {
                schedules.add(t);
            }
        }
        if (schedules.isEmpty()) {
            return null;
        } else {
            return schedules;
        }
    }

    private Collection<ITask> getRemoverTasks(Collection<ITask> availableTasks, Agent agent, SimulationMap map) {
        Collection<ITask> removers = new ArrayList<>();
        Set<String> states = agent.getState();
        for (ITask t : availableTasks) {
            if (t.getType().equals("Cleanup") && t.itemsExist(agent, map)) {
                removers.add(t);
            } else {
                for (String state : states) {
                    if (t.getNeg().contains(state)) {
                        removers.add(t);
                    }
                }
            }

        }
        if (removers.isEmpty()) {
            return null;
        } else {
            return removers;
        }
    }

    public void passTime(double d) {
        for (ITask t : tasks) {
            t.passTime(d);
        }
    }
}
