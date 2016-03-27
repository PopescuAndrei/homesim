/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.fils.smarthome.model.NodeObject;

/**
 *
 * @author andre
 */
@Repository
public interface NodeObjectRepository extends JpaRepository<NodeObject, Long> {

    @Modifying
    @Query("update NodeObject no set no.poses = ?1 where no.node.id = ?2 and no.type = ?3")
    void updateByNodeIdAndType(@Param("poses") String poses, @Param("nodeId") Long nodeId, @Param("type") String type);
}
