/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fils.smarthome.service.ServiceToBeAutowired;

/**
 *
 * @author andre
 */
@Service
@Transactional
public class ServiceToBeAutowiredImpl implements ServiceToBeAutowired{

    @Override
    public void works() {
        System.out.println("It's working");
    }
    
}
