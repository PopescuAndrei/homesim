/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fils.smarthome.model.Edge;
import ro.fils.smarthome.model.Node;
import ro.fils.smarthome.repository.EdgeRepository;
import ro.fils.smarthome.service.EdgeService;

/**
 *
 * @author andre
 */
@Service
@Transactional
public class EdgeServiceImpl implements EdgeService {

    @Autowired
    private EdgeRepository edgeRepository;
    
    @Override
    public Edge createNew(Edge edge){
        return edgeRepository.save(edge);
    }
    
    @Override
    public boolean exists(ArrayList<Edge> edges, Node node1, Node node2) {
        boolean result = false;
        if (edges.isEmpty()) {
            return false;
        }
        for (Edge edge : edges) {
            if (edge.getA().equals(node1) && edge.getB().equals(node2) || edge.getB().equals(node1) && edge.getA().equals(node2)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    @Override
    public void update(Edge edge){
        edgeRepository.save(edge);
    }

    @Override
    public void deleteEdge(Edge edge) {
        edgeRepository.delete(edge);
    }

}
