/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.planManagement;

import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ro.fils.smarthome.astar.AStarNode;
import ro.fils.smarthome.model.Appliance;
import ro.fils.smarthome.model.Room;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "nodes")
public class Node extends BaseEntity implements AStarNode {

    private int posX;
    private int posY;

    @OneToMany(mappedBy = "node", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Appliance> applianceTypes;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Node> neighbors;

    @ManyToOne
    private Room room;

    public Node() {

    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public List<Appliance> getApplianceTypes() {
        System.out.println("Node has " + applianceTypes.size() + " appliances");
        return applianceTypes;
    }

    public void setApplianceTypes(List<Appliance> applianceTypes) {
        this.applianceTypes = applianceTypes;
    }

    public Point getLocation() {
        return new Point(getPosX(), getPosY());
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room r) {
        this.room = r;
    }

    @Override
    public <T extends AStarNode> Collection<T> getNeighbors() {
        return (Collection<T>) neighbors;
    }

    public void addNeighbor(Node node) {
        for (Node n : neighbors) {
            if (Objects.equals(n.getId(), node.getId())) {
                return;
            }
        }
        neighbors.add(node);
    }

    /**
     * Needs to call update after each call
     *
     * @param applianceType
     */
    public void addAppliance(String applianceType) {
        if (applianceTypes.add(new Appliance(applianceType, this))) {

        }
    }
    
    /**
     * Needs to call update after each call
     *
     * @param type
     */
    public void removeAppliance(String type) {
        Iterator<Appliance> it = applianceTypes.iterator();
        while (it.hasNext()) {
            Appliance appliance = it.next();
            if (appliance.getType().equals(type)) {
                it.remove();
            }
        }
    }

    public double distance(Point target) {
        Point current = new Point(getPosX(), getPosY());
        return current.distance(target);
    }

    @Override
    public <T extends AStarNode> double getDistance(T node) throws RuntimeException {
        if (node instanceof Node) {
            Node nodeTemp = (Node) node;
            double distance = nodeTemp.distance(new Point(this.getPosX(), this.getPosY()));
            return distance;
        } else {
            throw new RuntimeException("Incompatible class type" + node.getClass());
        }

    }

    @Override
    public String toString() {
        return "Node{id=" + super.getId() + ", posX=" + posX + ", posY=" + posY + ", neighbors=" + neighbors + ", room=" + room + '}';
    }

    
}
