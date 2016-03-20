/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fils.smarthome.model.ModelForTest;

/**
 *
 * @author andre
 */
@Repository
public interface ModelForTestRepository extends JpaRepository<ModelForTest, Long>{
    
}
