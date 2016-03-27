/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service;

import java.util.List;
import ro.fils.smarthome.planManagement.Node;

/**
 *
 * @author andre
 */
public interface NodeService {

    Node findNodeById(Long nodeId);

    List<Node> findAll();

    Node createNewNode(Node node);

    void updateNode(Node node);
    
    void removeNode(Node node);
}
