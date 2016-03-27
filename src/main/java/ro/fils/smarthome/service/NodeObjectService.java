/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service;

import java.util.List;
import ro.fils.smarthome.model.NodeObject;

/**
 *
 * @author andre
 */
public interface NodeObjectService {

    NodeObject createNew(NodeObject nodeObject);

    List<NodeObject> findAll();

    void update(NodeObject nodeObject);

    void deleteNodeObject(NodeObject nodeObject);

    void updateNodeObjectPosesByIdAndType(String poses, Long nodeId, String type);
}
