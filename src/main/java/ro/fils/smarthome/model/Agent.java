/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import ro.fils.smarthome.util.TaskEnum;

/**
 *
 * @author Silvia
 */
public class Agent {

    private String name;
    private Image avatarImg;
    private int personType;
    private double pauseTime;

    private Point2D currentLocation;
    private Deque<Node> route;
    private List<Need> needs;
    private ITask currentTask;
    private double fetchTime;
    private double remainingTaskDuration;
    private String targetItem;
    private Set<String> state = new HashSet<>();
    private HashMap<String, Integer> inventory = new HashMap<>();
    private ITask goalTask;
    private Gadget gadget;

    public Agent(String name, String avatarImg, int personType, Point2D currentLocation, List<Need> needs) {
        this.name = name;
        this.avatarImg = new Image(getClass().getResourceAsStream(avatarImg));
        this.personType = personType;
        this.currentLocation = currentLocation;
        this.needs = needs != null ? new ArrayList<>(needs) : null;
        this.pauseTime = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }

    public double getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(double pauseTime) {
        this.pauseTime = pauseTime;
    }

    public Point2D getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point2D currentLocation) {
        this.currentLocation = currentLocation;
        if (route != null && !route.isEmpty()) {
            if (currentLocation.distance(route.peek().getLocation()) == 0) {
                route.remove();
            }
        }
    }

    public Deque<Node> getRoute() {
        if (route == null || route.isEmpty()) {
            return null;
        }
        return route;
    }

    public void setRoute(Deque<Node> route) {
        this.route = route;
    }

    public List<Need> getNeeds() {
        return needs;
    }

    public void setNeeds(List<Need> needs) {
        this.needs = needs;
    }

    public void addNeed(Need need) {
        needs.add(need);
    }

    public ITask getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(ITask currentTask) {
        this.currentTask = currentTask;

        if (currentTask != null) {
            if (currentTask.getType().equals(TaskEnum.Automatic.name())) {
                this.remainingTaskDuration = 60 * new Random().nextInt(3);
            } else {
                this.remainingTaskDuration = currentTask.getDurationSeconds() + (60 * new Random().nextInt(4));
            }
        }
    }

    public void setCurrentTask(ITask task, Gadget gadget) {
        this.setCurrentTask(currentTask);
        this.setGadget(gadget);
    }

    public double getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(double fetchTime) {
        this.fetchTime = fetchTime;
    }

    public double getRemainingTaskDuration() {
        return remainingTaskDuration;
    }

    public void setRemainingTaskDuration(double remainingTaskDuration) {
        this.remainingTaskDuration = remainingTaskDuration;
    }

    public String getTargetItem() {
        return targetItem;
    }

    public void setTargetItem(String targetItem) {
        this.targetItem = targetItem;
        this.fetchTime = 2.0;
    }

    public ITask getGoalTask() {
        return goalTask;
    }

    public void setGoalTask(ITask goalTask) {
        this.goalTask = goalTask;
    }

    public Gadget getGadget() {
        return gadget;
    }

    public void setGadget(Gadget gadget) {
        this.gadget = gadget;
    }

    public Set<String> getState() {
        return state;
    }

    public void setState(Set<String> state) {
        this.state = state;
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item) {
        inventory.put(item.getName(), (inventory.get(item.getName()) != null ? inventory.get(item.getName()) : 0) + 1);
    }

    public void removeItem(String itemName, int amount) {
        inventory.remove(itemName);
    }

    public boolean hasItem(String item, int amount) {
        return (inventory.containsKey(item) && inventory.get(item) >= amount);
    }

    public void progressTask(double seconds) {
        this.remainingTaskDuration = this.remainingTaskDuration - seconds;
    }

    public void progressFetch(double seconds) {
        this.fetchTime = this.fetchTime - seconds;
    }

    public void passTime(double seconds) {

        this.needs.stream().forEach((n) -> {
            n.deteriorateNeed(seconds);
        });

        if (this.pauseTime > 0) {
            this.pauseTime = this.pauseTime - seconds;
        }
    }

    public Set<String> getPoseData() {

        Set<String> poses = new HashSet<>();
        if (this.currentTask != null) {
            poses.addAll(this.currentTask.getPoses());
        }
        if (this.gadget != null) {
            poses.addAll(this.gadget.getPoses());
        }

        Set<String> filtered = new HashSet<>();

        poses.stream().filter((pose) -> (!pose.trim().isEmpty())).forEach((pose) -> {
            filtered.add(pose);
        });
        return filtered;
    }

    public boolean isMoving() {
        return (route != null && route.size() > 0);
    }

    public void addState(String stateString) {
        if (stateString.charAt(0) == '+') {
            state.add(stateString.substring(1));
        } else {
            state.remove(stateString.substring(1));
        }
    }

}
