/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import ro.fils.smarthome.planManagement.BaseEntity;
import ro.fils.smarthome.planManagement.Node;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Silvia
 */
@Entity
@Table(name = "appliances")
public class Appliance extends BaseEntity {

    private String type;
    private String poses;

    @OneToOne
    private Node node;

    @Transient
    private Map<String, Integer> inventory;

    @Transient
    private Set<String> posesSet;

    public Appliance() {

    }

    public Appliance(String applianceType, Node node) {
        this.type = applianceType;
        this.node = node;
        this.inventory = new HashMap<>();
        this.posesSet = new HashSet<>();
    }

    public Appliance(String applianceType, Node node, Set<String> poses) {
        this(applianceType, node);
        this.posesSet = new HashSet<>();
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

    public Set<String> getPosesSet() {
        return posesSet;
    }

    public void setPosesSet(Set<String> posesSet) {
        this.posesSet = posesSet;
        String posesString = null;
        for(String s: posesSet){
            posesString = posesString + " "+ s;
        }
        this.poses = posesString;
    }

    public String getPoses() {
        return poses;
    }

    public void setPoses(String poses) {
        this.poses = poses;
        this.posesSet = new HashSet<>(Arrays.asList(poses.split(" ")));
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
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.type);
        hash = 79 * hash + Objects.hashCode(this.node);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Appliance other = (Appliance) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return Objects.equals(this.node, other.node);
    }

    

    @Override
    public String toString() {
        return "Appliance{" + "inventory=" + inventory + ", type=" + type + ", node=" + node + ", poses=" + poses + '}';
    }

}
