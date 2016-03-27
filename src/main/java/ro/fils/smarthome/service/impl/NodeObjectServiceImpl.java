/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fils.smarthome.model.NodeObject;
import ro.fils.smarthome.repository.NodeObjectRepository;
import ro.fils.smarthome.service.NodeObjectService;

/**
 *
 * @author andre
 */
@Service
@Transactional
public class NodeObjectServiceImpl implements NodeObjectService{

    @Autowired
    NodeObjectRepository nodeObjectRepo;
    
    @Override
    public NodeObject createNew(NodeObject nodeObject) {
        return nodeObjectRepo.save(nodeObject);
    }

    @Override
    public List<NodeObject> findAll() {
        return nodeObjectRepo.findAll();
    }

    @Override
    public void update(NodeObject nodeObject) {
        nodeObjectRepo.save(nodeObject);
    }

    @Override
    public void deleteNodeObject(NodeObject nodeObject) {
        nodeObjectRepo.delete(nodeObject);
    }

    @Override
    public void updateNodeObjectPosesByIdAndType(String poses, Long nodeId, String type) {
        nodeObjectRepo.updateByNodeIdAndType(poses, nodeId, type);
    }
    
    
}
