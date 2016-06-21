/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import ro.fils.smarthome.simulation.AStarNode;
import ro.fils.smarthome.repository.NodeRepository;

/**
 *
 * @author andre
 */
public class Node implements AStarNode {

    private int id;
    private int posX;
    private int posY;
    private int houseId;
    
    private List<Appliance> applianceTypes;
    private final Collection<Node> neighbors = new ArrayList<>();

    public Node() {

    }

    public Node(int id, Point p) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public <T extends AStarNode> Collection<T> getNeighbors() {
        return (Collection<T>) neighbors;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
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
        if (applianceTypes == null) {
            applianceTypes = new ArrayList<>();
        }

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
        return "Node{id=" + id + ", posX=" + posX + ", posY=" + posY + "}";
    }

}
