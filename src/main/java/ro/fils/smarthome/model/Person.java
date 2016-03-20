/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 *
 * @author Silvia
 */
public class Person {
    
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
    private Set<String> state;
    private HashMap<String, Integer> inventory;    
    private ITask goalTask;
    private Gadget gadget;

    public Person(String name, String avatarImg, Point2D currentLocation, List<Need> needs, int type) {
        this.name = name;
        // not sure if this works - 
        // in swing: new ImageIcon(getClass().getResource(avatarImage)).getImage();
        this.avatarImg = new Image(getClass().getResourceAsStream(avatarImg));
        this.currentLocation = currentLocation;
        this.needs = needs != null? new ArrayList<>() : null;
        this.personType = type;
        this.pauseTime = 0.0;
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
    }

    public Deque<Node> getRoute() {
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

    public ITask getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(ITask currentTask) {
        this.currentTask = currentTask;
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

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", personType=" + personType + ", pauseTime=" + pauseTime + ", currentLocation=" + currentLocation + ", route=" + route + ", needs=" + needs + ", currentTask=" + currentTask + ", fetchTime=" + fetchTime + ", remainingTaskDuration=" + remainingTaskDuration + ", targetItem=" + targetItem + ", state=" + state + ", inventory=" + inventory + ", goalTask=" + goalTask + ", usingGadget=" + gadget + '}';
    }

}
