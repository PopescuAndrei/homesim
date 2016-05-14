/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import ro.fils.smarthome.planManagement.Node;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Silvia
 */
public class Appliance{

    private String type;
    private Node node;
    private Map<String, Integer> inventory;
    private Set<String> poses;

    public Appliance() {

    }

    public Appliance(String applianceType, Node node) {
        this.type = applianceType;
        this.node = node;
        this.inventory = new HashMap<>();
        this.poses = new HashSet<>();
    }

    public Appliance(String applianceType, Node node, Set<String> poses) {
        this(applianceType, node);
        this.poses = poses;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.type + getNode().getId();
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Set<String> getPoses() {
        return poses;
    }

    public void setPoses(String posesString) {
        this.poses = new HashSet<>(Arrays.asList(posesString.split(" ")));
    }

    public boolean hasItem(String item) {
        return inventory.containsKey(item);
    }

    public void removeItem(String item, int amount) {
        inventory.put(item, getAmountOfItem(item) - amount);
    }

    public void addItem(String item, int amount) {
        inventory.put(item, getAmountOfItem(item) + amount);
    }

    public int getAmountOfItem(String item) {
        int amount = 0;
        if (inventory.get(item) != null) {
            amount = inventory.get(item);
        }
        return amount;
    }

    @Override
    public String toString() {
        return "Appliance{" + "inventory=" + inventory + ", type=" + type + ", node=" + node + ", poses=" + poses + '}';
    }

}
