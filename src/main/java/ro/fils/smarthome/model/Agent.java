/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import ro.fils.smarthome.tasks.ITask;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.swing.ImageIcon;
import ro.fils.smarthome.util.support.Activities_Type;

/**
 *
 * @author Silvia
 */
public class Agent {

    public static final int WANDERER = 1;

    private int id;
    private String name;
    private Image avatarImg;
    private double pauseTime;
    private String imagePath;

    private Point currentLocation;
    private Deque<Node> route;
    private List<Need> needs;
    private ITask currentTask;
    private double fetchTime;
    private double remainingTaskDuration;
    private String targetItem;
    private Set<String> state;
    private HashMap<String, Integer> inventory;
    private ITask goalTask;
    private Appliance applianceInUse;

    double[] chooseFrom = new double[]{1.0, 1.2, 1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.7, 2.8, 3.0};
    double[] chooseFrom2 = new double[]{1.0, 1.2, 1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.7, 2.8, 3.0, 3.2, 3.4, 3.6, 3.8, 4.0};

    public Agent() {

    }

    public Agent(String name, String avatarImg, Point currentLocation, List<Need> needs) {
        this.name = name;
        this.imagePath = avatarImg;
        this.avatarImg = new ImageIcon(getClass().getResource(avatarImg)).getImage();
        this.currentLocation = currentLocation;
        this.needs = needs;
        this.inventory = new HashMap<>();
        this.state = new HashSet<>();
        this.pauseTime = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(double pauseTime) {
        this.pauseTime = pauseTime;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point moveToPoint) {
        this.currentLocation = moveToPoint;
        if ((route != null || !route.isEmpty())
                && currentLocation.distance(route.peek().getLocation()) == 0) {
            route.remove();
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
            if (currentTask.getType().equals(Activities_Type.Automatic.name())) {
                this.remainingTaskDuration = 60 * returnRandomValue(chooseFrom);
            } else {
                this.remainingTaskDuration = currentTask.getDurationSeconds() + (60 * returnRandomValue(chooseFrom2));
            }
        }
    }

    public void setCurrentTask(ITask task, Appliance applianceInUse) {
        this.setCurrentTask(task);
        this.setApplianceInUse(applianceInUse);
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

    public Appliance getApplianceInUse() {
        return applianceInUse;
    }

    public void setApplianceInUse(Appliance applianceInUse) {
        this.applianceInUse = applianceInUse;
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
        inventory.put(item.getName(), (inventory.get(item.getName()) != null
                ? inventory.get(item.getName()) : 0) + 1);
    }

    public void removeItem(String itemName, int amount) {
        inventory.remove(itemName);
    }

    public boolean hasItem(String item, int amount) {
        return (inventory.containsKey(item) && inventory.get(item) >= amount);
    }

    public void progressTask(double seconds) {
        this.remainingTaskDuration -= seconds;
    }

    public void progressFetch(double seconds) {
        this.fetchTime = this.fetchTime - seconds;
    }

    public void passTime(double seconds) {
        for (Need need : needs) {
            need.deteriorateNeed(seconds);
        }
        if (this.pauseTime > 0.0) {
            this.pauseTime -= seconds;
        }
    }

    public Set<String> getPoseData() {
        Set<String> poses = new HashSet<>();
        if (this.currentTask != null) {
            poses.addAll(this.currentTask.getPoses());
        }
        if (this.applianceInUse != null) {
            poses.addAll(this.applianceInUse.getPoses());
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

    public Image getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(Image avatarImg) {
        this.avatarImg = avatarImg;
    }

    private double returnRandomValue(double[] array) {
        return array[new Random().nextInt(array.length)];
    }
}
