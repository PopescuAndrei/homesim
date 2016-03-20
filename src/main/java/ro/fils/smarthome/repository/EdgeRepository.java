/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fils.smarthome.model.Edge;

/**
 *
 * @author andre
 */
public interface EdgeRepository extends JpaRepository<Edge, Long>{
    
}
