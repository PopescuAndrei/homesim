/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.planManagement;

import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class Edge{

    private Long id;
    private Node a;
    private Node b;
    private double cachedLength = -1.0;

    public Edge(Long id, Node a, Node b) {
        this.id = id;
        this.a = a;
        this.b = b;
    }

    public Node getA() {
        return a;
    }

    public void setA(Node a) {
        this.a = a;
    }

    public Node getB() {
        return b;
    }

    public void setB(Node b) {
        this.b = b;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public double getLength() {
        if (cachedLength == -1.0) {
            cachedLength = Math.sqrt(Math.exp(a.getPosX() - b.getPosX()) + Math.exp(a.getPosY() - b.getPosY()));
        }
        return cachedLength;
    }


    @Override
    public String toString() {
        return "Edge{" + "a=" + a + ", b=" + b + ", cachedLength=" + cachedLength + '}';
    }

    public boolean exists(ArrayList<Edge> edges, Node p1, Node p2) {
        boolean result = false;
        if (edges.isEmpty()) {
            return false;
        } else {
            for (Edge edge : edges) {
                if ((edge.a.equals(p1) && edge.b.equals(p2)) || (edge.b.equals(p1) && edge.a.equals(p2))) {
                    result = true;
                    break;
                }
            }
            return result;
        }
    }

}
