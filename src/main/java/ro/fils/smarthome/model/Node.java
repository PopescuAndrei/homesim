/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javafx.geometry.Point2D;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ro.fils.smarthome.astar.AStarNode;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "nodes")
public class Node extends BaseEntity implements AStarNode {

    private int posX;
    private int posY;
    
    @OneToMany(mappedBy = "node")
    private List<Appliance> appliancesTypes;
    
    @OneToMany
    private Collection<Node> neighbors;

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

    public List<Appliance> getAppliancesTypes() {
        return appliancesTypes;
    }

    public void setAppliancesTypes(List<Appliance> appliances) {
        this.appliancesTypes = appliances;
    }

    public Point2D getLocation() {
        return new Point2D(getPosX(), getPosY());
    }

    @Override
    public <T extends AStarNode> Collection<T> getNeighbors() {
        return (Collection<T>) neighbors;
    }
    
    public void addNeighbor(Node node){
        for(Node n : neighbors){
            if(Objects.equals(n.getId(), node.getId())){
                return;
            }
        }
        neighbors.add(node);
    }
    
    /**
     * Needs to call update at the end
     * @param appliancesType 
     */
    public void addAppliance(String appliancesType){
        if(appliancesTypes.add(new Appliance(appliancesType, this))){
            
        }
    }
    
    /**
     * Needs to call update at the end
     * @param type 
     */
    public void removeAppliance(String type){
        Iterator<Appliance> it = appliancesTypes.iterator();
        while(it.hasNext()){
            Appliance appliance = it.next();
            if(appliance.getType().equals(type)){
                it.remove();
            }
        }
    }
    public double distance(Point2D target) {
        Point2D current = new Point2D(getPosX(), getPosY());
        return current.distance(target);
    }

    @Override
    public <T extends AStarNode> double getDistance(T node) throws RuntimeException {
        if (node instanceof Node) {
            Node nodeTemp = (Node) node;
            double distance = nodeTemp.distance(new Point2D(this.getPosX(), this.getPosY()));
            return distance;
        } else {
            throw new RuntimeException("Incompatible class type" + node.getClass());
        }

    }
}
