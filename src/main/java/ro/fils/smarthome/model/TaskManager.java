/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.fils.smarthome.astar.AStarImpl;
import ro.fils.smarthome.simulation.SimulationMap;
import ro.fils.smarthome.util.Activities_Type;

/**
 *
 * @author Silvia
 */
public class TaskManager {

    private static final Logger LOG = Logger.getLogger(TaskManager.class.getName());

    private Collection<ITask> tasks;

    public TaskManager(TaskReader taskRead) {
        tasks = taskRead.getTasks();
    }

    public void findTask(Agent agent, SimulationMap map, double time) {

        Collection<ITask> availableTasks = new ArrayList<>(filterAvailable(tasks, time));
        if (agent.getGoalTask() != null && !agent.getGoalTask().available(time)) {
            agent.setGoalTask(null);
        }

        if (agent.getGoalTask() != null) {

            if (agent.getGoalTask().agentMeetsRequirements(agent)) {
                LOG.log(Level.INFO, "Requirements met for task {0}", agent.getGoalTask().getName());
                setTaskForAgent(agent, agent.getGoalTask(), map);
                agent.setGoalTask(null);
            } else if (agent.getGoalTask().itemExists(agent, map)) {
                moveForItems(agent, agent.getGoalTask(), map);
            } else {
                try {
                    Deque<ITask> tusks = PPlanWrapper.getPlan(map, agent, availableTasks, agent.getGoalTask());
                    LOG.info(tusks.toString());
                    setTaskForAgent(agent, tusks.getFirst(), map);
                } catch (Exception ex) {
                    ArrayList<String> mapItems = new ArrayList<>();
                    map.getItems().stream().forEach((it) -> {
                        mapItems.add(it.getName());
                    });
                    LOG.log(Level.SEVERE, "Crashed when finding plan and setting task " + agent.getGoalTask().getName()
                            + ", " + agent.getGoalTask().getRequiredItemsSet() + ", " + agent.getInventory().keySet() + ", "
                            + mapItems, ex);
                }
            }
        } else {
            // if no goal task then create a goal
            LOG.info("Finding a new goal task");
            List<Need> needs = new ArrayList<>(agent.getNeeds());
            Collection<ITask> scheduledTasks = getScheduled(availableTasks);

            System.out.println(scheduledTasks);

            Collection<ITask> tasksToRemoveStuff = getRemoverTasks(availableTasks, agent, map);
            System.out.println(tasksToRemoveStuff);

            if (scheduledTasks != null) {
                agent.setGoalTask(scheduledTasks.iterator().next());
            } else if (tasksToRemoveStuff != null) {
                agent.setGoalTask(tasksToRemoveStuff.iterator().next());
            } else {
                while (!needs.isEmpty()) {
                    Need lowest = getLowestNeed(needs);

                    LOG.log(Level.FINE, "{0} is lowest need", lowest.getName());
                    ITask taskForNeed = taskForNeed(lowest, availableTasks, time, agent, map);
                    if (taskForNeed != null) {
                        agent.setGoalTask(taskForNeed);
                        break;
                    } else {
                        needs.remove(lowest);
                    }
                }
            }
        }
    }

    public Collection<ITask> filterAvailable(Collection<ITask> allTasks, double time) {
        Collection<ITask> filteredTasks = new ArrayList<>();
        allTasks.stream().filter((task) -> (task.available(time))).forEach((task) -> {
            filteredTasks.add(task);
        });
        return filteredTasks;
    }

    private void setTaskForAgent(Agent agent, ITask goalTask, SimulationMap map) {

        if (goalTask.itemExists(agent, map)) {
            moveForItems(agent, goalTask, map);
        } else {
            LOG.log(Level.INFO, "{0} is doing task {1}, for {2}", new Object[]{agent.getName(), goalTask.toString(), agent.getGoalTask().toString()});

            try {

                Collection<Appliance> appliances = map.getAppliances();
                Collection<String> valids = goalTask.getUsedAppliances();
                Set<Appliance> validApps = new HashSet<>();

                for (Appliance appliance : appliances) {
                    for (String valid : valids) {
                        if (appliance.getType().contains(valid)) {
                            validApps.add(appliance);
                        }
                    }
                }
                Collection<Node> goals = map.getLocationOfAppliances(valids);
                for (Agent a : map.getPeople()) {
                    if (!a.equals(agent) && a.getRoute() != null) {
                        goals.remove(a.getRoute().getLast());
                    }
                }
                AStarImpl aStarAlg = new AStarImpl();
                Node closestNode = map.getClosestNode(agent.getCurrentLocation());
                agent.setRoute(aStarAlg.getRoute(goals, closestNode));

                for (Appliance app : validApps) {
                    if (app.getNode().equals(agent.getRoute().getLast())) {
                        agent.setCurrentTask(goalTask, app);
                        goalTask.consumeItem(agent);
                        break;
                    }
                }

                if (closestNode.equals(agent.getRoute().getLast())) {
                    agent.setRoute(null);
                }

            } catch (Exception ex) {

            }
        }
    }

    public void moveForItems(Agent agent, ITask goalTask, SimulationMap map) {
        LOG.log(Level.INFO, "{0} is fetching item:", agent.getName());
        AStarImpl aStarAlg = new AStarImpl();
        for (String s : goalTask.getRequiredItemsSet()) {
            if (!(agent.hasItem(s, goalTask.getRequiredItems().get(s))) && map.hasItem(s, goalTask.getRequiredItems().get(s))) {
                agent.setTargetItem(s);
                LOG.info(s);
                try {
                    agent.setRoute(aStarAlg.getRoute(map.getLocationsOfItem(s), map.getClosestNode(agent.getCurrentLocation())));
                    if (map.getClosestNode(agent.getCurrentLocation()).equals(agent.getRoute().getLast())) {
                        agent.setRoute(null);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private Collection<ITask> getScheduled(Collection<ITask> availableTasks) {
        Collection<ITask> schedules = new ArrayList<>();
        for (ITask t : availableTasks) {
            if (t.getType().equals(Activities_Type.Scheduled.name())) {
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
            if (t.getType().equals(Activities_Type.Cleanup.name()) && t.itemsExist(agent, map)) {
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

    private Need getLowestNeed(List<Need> needs) {
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

    private ITask taskForNeed(Need lowest, Collection<ITask> tasks, double time, Agent agent, SimulationMap map) {
        List<ITask> tasksForNeed = new ArrayList<>();
        for (ITask task : tasks) {
            if (task.fulfilledNeed() != null && task.fulfilledNeed().equals(lowest.getName())) {
                try {
                    PPlanWrapper.getPlan(map, agent, tasks, task);
                    tasksForNeed.add(task);
                } catch (Exception e) {
                    LOG.log(Level.INFO, "{0} has no viable plan", task.getName());
                }
            }
        }
        LOG.log(Level.INFO, "{0} possible tasks to fulfill {1}", new Object[]{tasksForNeed.size(), lowest.getName()});
        return (tasksForNeed.isEmpty() ? null : tasksForNeed.get(new Random().nextInt(tasksForNeed.size())));
    }

    public void passTime(double d) {
        for (ITask t : tasks) {
            t.passTime(d);
        }
    }

}
