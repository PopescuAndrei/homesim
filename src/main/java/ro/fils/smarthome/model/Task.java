/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ro.fils.smarthome.simulation.SimulationMap;

/**
 *
 * @author Silvia
 */
public class Task implements ITask {

    private String fulfilledNeed;
    private int fulfilledAmount = -1;

    private Map<String, Integer> requiredItem = new HashMap<>();
    private Map<String, Integer> resultingItem = new HashMap<>();

    private int startTime = -1;
    private int endTime = -1;

    private String taskName;
    private int durationMinutes;
    private double remainingSeconds;
    private String performedAt;
    private String type;
    private String label;
    private Set<String> poseSet = new HashSet<>();
    private Set<String> precond = new HashSet<>();
    private Set<String> pos = new HashSet<>();
    private Set<String> neg = new HashSet<>();
    private double cooldownMax = 0;
    private double cooldown = 0;

    public Task(String taskName, int durationMinutes, String performedAt, String type, String label) {
        this.taskName = taskName;
        this.durationMinutes = durationMinutes;
        this.remainingSeconds = (double) (durationMinutes * 60);
        this.performedAt = performedAt;
        this.type = type;
        this.label = label;
        this.fulfilledNeed = null;
    }

    public Task(String taskName, int durationMinutes, String performedAt, String type, String label, double cooldown) {
        this(taskName, durationMinutes, performedAt, type, label);
        this.cooldown = cooldown;
    }

    public Task(String taskName, String type, int durationSeconds, int startTime, int endTime, String performedAt, String label) {
        this(taskName, durationSeconds, performedAt, type, label);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addResult(String need, int amount) {
        this.fulfilledNeed = need;
        this.fulfilledAmount = amount;
    }

    public void addRequiredItem(String item, int amount) {
        requiredItem.put(item, amount);
    }

    public void addResultingItem(String item, int amount) {
        resultingItem.put(item, amount);
    }

    public boolean completable(Agent a, SimulationMap map) {
        Iterator<String> it = requiredItem.keySet().iterator();
        while (it.hasNext()) {
            String item = it.next();
            if (!a.hasItem(item, requiredItem.get(item))) {
                return false;
            }
        }
        return true;
    }

    private boolean agentHasItems(Agent agent) {
        return requiredItem.keySet().stream().noneMatch((item) -> (!agent.hasItem(item, requiredItem.get(item))));
    }

    private boolean agentHasStates(Agent agent) {
        Set<String> states = agent.getState();
        return states.containsAll(precond);
    }

    @Override
    public String fulfilledNeed() {
        return fulfilledNeed;
    }

    @Override
    public boolean available(double time) {
        if ((startTime < 0 || endTime < 0) && cooldown == 0) {
            return true;
        }
        return ((Time.getHours(time) + (24 - startTime)) % 24) < ((endTime + (24 - startTime)) % 24)
                && cooldown == 0;

    }

    @Override
    public boolean itemsExist(Agent p, SimulationMap map) {
        return requiredItem.keySet().stream().noneMatch((item) -> ((!map.hasItem(item, requiredItem.get(item))) && !p.hasItem(item, requiredItem.get(item))));
    }

    @Override
    public boolean agentMeetsRequirements(Agent person) {
        return agentHasItems(person) && agentHasStates(person);
    }

    @Override
    public Collection<Appliance> getViableAppliances(Collection<Appliance> allGadges) {
        Collection<Appliance> viableObjects = new ArrayList<>();
        if (performedAt != null) {
            allGadges.stream().filter((ap) -> (performedAt.equals(ap.getType()))).forEach((ap) -> {
                Iterator<String> it = requiredItem.keySet().iterator();
                boolean fullfield = true;
                while (it.hasNext()) {
                    if (!ap.hasItem(it.next())) {
                        fullfield = false;
                    }
                }
                if (fullfield) {
                    viableObjects.add(ap);
                }
            });
        } else {
            System.out.println("ro.fils.smarthome.model.Task.getViableAppliances()"
                    + "Task " + taskName + " cannot be performed anywhere?");
        }
        return viableObjects;
    }

    @Override
    public Map<String, Integer> getRequiredItems() {
        return requiredItem;
    }

    @Override
    public double getDurationSeconds() {
        return (double) (durationMinutes * 60);
    }

    @Override
    public Set<String> getCreatedItems() {
        Set<String> str = new HashSet<>();
        resultingItem.keySet().stream().forEach((item) -> {
            str.add(item);
        });
        return str;
    }

    @Override
    public Set<String> getRequiredItemsSet() {
        return requiredItem.keySet();
    }

    @Override
    public Collection<String> getUsedAppliances() {
        Collection<String> appliances = new ArrayList<>();
        appliances.add(performedAt);

        return appliances;
    }

    @Override
    public void completeTask(Agent p, SimulationMap map) {

        pos.stream().forEach((state) -> {
            p.addState("+" + state);
        });
        neg.stream().forEach((state) -> {
            p.addState("-" + state);
        });

        if (fulfilledNeed != null) {
            List<Need> needs = p.getNeeds();
            needs.stream().filter((need) -> (need.getName().equals(fulfilledNeed))).forEach((need) -> {
                need.improveNeed(fulfilledAmount);
            });
        }
        if (!resultingItem.isEmpty()) {
            resultingItem.keySet().stream().forEach((item) -> {
                for (int i = 0; i < resultingItem.get(item); i++) {
                    //TODO
                    map.addItem(new Item(item, map.getClosestNode(p.getCurrentLocation())));
//                    map.getItems().size(); // why??
                }
            });
        }
    }

    @Override
    public void consumeItem(Agent p) {
        requiredItem.keySet().stream().forEach((item) -> {
            p.removeItem(item, requiredItem.get(item));
        });
    }

    @Override
    public String getName() {
        return taskName;
    }

    @Override
    public Set<String> getPoses() {
        return poseSet;
    }

    @Override
    public Set<String> getNeg() {
        return neg;
    }

    @Override
    public Set<String> getPos() {
        return pos;
    }

    @Override
    public Set<String> getPrecond() {
        return this.precond;
    }

    @Override
    public String label() {
        return label;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean itemExists(Agent person, SimulationMap map) {
        return requiredItem.keySet().stream().anyMatch((item) -> (map.hasItem(item, requiredItem.get(item)) && !person.hasItem(item, requiredItem.get(item))));
    }

    @Override
    public void recentlyCompleted() {
        this.cooldown = cooldownMax;
    }

    @Override
    public void passTime(double d) {
        this.cooldown = this.cooldown - d;
        if (cooldown < 0.0) {
            cooldown = 0.0;
        }

    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    public void addPose(String poseString) {
        poseSet.add(poseString);
    }

    public void addNeededState(String poseString) {
        precond.add(poseString);
    }

    public void addPlusState(String substring) {
        pos.add(substring);
    }

    public void addMinusState(String substring) {
        neg.add(substring);
    }
}
