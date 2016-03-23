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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.fils.smarthome.astar.AStarImpl;
import ro.fils.smarthome.simulation.SimulationMap;

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
                
                for (Appliance appliance: appliances) {
                    for (String valid: valids) {
                        if (appliance.getType().contains(valid)) {
                            validApps.add(appliance);
                        }
                    }
                }
               
           } catch (Exception ex) {
               
           }
        }
    }

    private void moveForItems(Agent agent, ITask goalTask, SimulationMap map) {
        LOG.log(Level.INFO, "{0} is fetching item:", agent.getName());
        AStarImpl aStartAlg = new AStarImpl();
        for (String s : goalTask.getRequiredItemsSet()) {
            if (!(agent.hasItem(s, goalTask.getRequiredItems().get(s))) && map.hasItem(s, goalTask.getRequiredItems().get(s))) {
                agent.setTargetItem(s);
                LOG.info(s);
                try {
                    agent.setRoute(aStartAlg.getRoute(map.getLocationsOfItem(s), map.getClosestNode(agent.getCurrentLocation())));
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Collection<ITask> getRemoverTasks(Collection<ITask> availableTasks, Agent agent, SimulationMap map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Need getLowestNeed(List<Need> needs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ITask taskForNeed(Need lowest, Collection<ITask> availableTasks, double time, Agent agent, SimulationMap map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
