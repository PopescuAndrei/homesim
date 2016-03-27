/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service;

import java.util.ArrayList;
import java.util.List;
import ro.fils.smarthome.planManagement.Edge;
import ro.fils.smarthome.planManagement.Node;

/**
 *
 * @author andre
 */
public interface EdgeService {
    
    Edge createNew(Edge edge);
    List<Edge> findAll();
    boolean exists(ArrayList<Edge> edges, Node node1, Node node2);
    void update(Edge edge);
    void deleteEdge(Edge edge);
}
