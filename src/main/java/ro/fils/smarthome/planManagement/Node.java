/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.planManagement;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import ro.fils.smarthome.astar.AStarNode;
import ro.fils.smarthome.model.Appliance;
import ro.fils.smarthome.model.Room;
import ro.fils.smarthome.repository.NodeRepository;

/**
 *
 * @author andre
 */
public class Node implements AStarNode {

    private Long id;
    private int posX;
    private int posY;

    private List<Appliance> applianceTypes;
    private Collection<Node> neighbors = new ArrayList<>();

    private Room room;

    public Node() {

    }
    
    public Node(Long id, Point p){
        this.id = id;
        posX = (int) p.getX();
        posY = (int) p.getY();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
     * @param applianceType
     */
    public void addAppliance(String applianceType) {
        if (applianceTypes.add(new Appliance(applianceType, this))) {
            new NodeRepository().update(this);
        }
    }
    
    /**
     *
     * @param type
     */
    public void removeAppliance(String type) {
        Iterator<Appliance> it = applianceTypes.iterator();
        while (it.hasNext()) {
            Appliance appliance = it.next();
            if (appliance.getType().equals(type)) {
                it.remove();
                new NodeRepository().update(this);
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
        return "Node{id=" + id + ", posX=" + posX + ", posY=" + posY +  ", room=" + room + '}';
    }

    
}
