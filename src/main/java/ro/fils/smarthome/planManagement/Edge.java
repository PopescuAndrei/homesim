/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.planManagement;

import java.util.ArrayList;
import java.util.Iterator;
import ro.fils.smarthome.planManagement.BaseEntity;
import ro.fils.smarthome.planManagement.Node;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "edges")
public class Edge extends BaseEntity {

    @OneToOne
    private Node a;

    @OneToOne
    private Node b;

    @Transient
    private double cachedLength = -1.0;

    public Edge() {

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

    public double getLength() {
        if (cachedLength == -1.0) {
            cachedLength = Math.sqrt(Math.exp(a.getPosX() - b.getPosX()) + Math.exp(a.getPosY() - b.getPosY()));
        }
        return cachedLength;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.a);
        hash = 61 * hash + Objects.hashCode(this.b);
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
        final Edge other = (Edge) obj;
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        return Objects.equals(this.b, other.b);
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
