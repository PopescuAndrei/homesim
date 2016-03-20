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
import ro.fils.smarthome.model.Node;
import ro.fils.smarthome.repository.NodeRepository;
import ro.fils.smarthome.service.NodeService;

/**
 *
 * @author andre
 */
@Service
@Transactional
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public Node findNodeById(Long nodeId) {
        return nodeRepository.findOne(nodeId);
    }

    @Override
    public ArrayList<Node> getNodes() {
        return (ArrayList<Node>) nodeRepository.findAll();
    }

    @Override
    public Node createNewNode(Node node) {
        return nodeRepository.save(node);
    }

    @Override
    public void updateNode(Node node) {
        nodeRepository.save(node);
    }

}
