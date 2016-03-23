/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    @Transient
    private Map<String, Integer> inventory;

    @Column(name = "type")
    private String type;

    @OneToOne
    @ManyToOne
    private Node node;

    @Transient
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

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Set<String> getPoses() {
        return poses;
    }

    public void setPoses(Set<String> poses) {
        this.poses = poses;
    }

    public void setPosesByString(String posesString) {
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

    public String getName() {
        return type + node.getId();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + Objects.hashCode(this.node);
        hash = 71 * hash + Objects.hashCode(this.poses);
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
        if (!Objects.equals(this.node, other.node)) {
            return false;
        }
        return Objects.equals(this.poses, other.poses);
    }

    @Override
    public String toString() {
        return "Appliance{" + "inventory=" + inventory + ", type=" + type + ", node=" + node + ", poses=" + poses + '}';
    }

}
