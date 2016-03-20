/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service.impl;

import org.springframework.stereotype.Service;
import ro.fils.smarthome.model.Need;
import ro.fils.smarthome.service.NeedService;

/**
 *
 * @author Silvia
 */
@Service
public class NeedServiceImpl implements NeedService {

    /**
     *
     * @param need
     * @param seconds
     * @return Need with value decayed
     */
    @Override
    public Need deteriorateNeed(Need need, double seconds) {

        double needValue = need.getValue() - need.getDecayRate() / (60 * 60 * seconds);
        needValue = needValue < 0 ? 0 : needValue;
        need.setValue(needValue);      
        return need;
    }

    /**
     *
     * @param need
     * @param amount
     * @return Need with value increased
     */
    @Override
    public Need improveNeed(Need need, double amount) {

        double needValue = need.getValue() + amount;
        needValue = needValue > 100 ? 100 : needValue;
        need.setValue(needValue);
        return need;
    }

}
