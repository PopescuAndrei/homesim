/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fils.smarthome.service.HelloService;
import ro.fils.smarthome.service.ServiceToBeAutowired;

/**
 *
 * @author andre
 */
@Service
@Transactional
public class HelloServiceImpl implements HelloService {
    
    @Autowired
    ServiceToBeAutowired serviceToBeAutowired;
    
    @Override
    public void hello(){
        System.out.println("YiipeeKayYeey Motherfuckers");
        serviceToBeAutowired.works();
    }
}
