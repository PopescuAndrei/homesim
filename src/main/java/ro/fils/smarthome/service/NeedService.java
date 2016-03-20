/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service;

import ro.fils.smarthome.model.Need;

/**
 *
 * @author Silvia
 */
public interface NeedService {

    /**
     * 
     * @param need
     * @param seconds
     * @return Need with value decayed 
     */
    public Need deteriorateNeed(Need need, double seconds);

    /**
     * 
     * @param need
     * @param amount
     * @return Need with value increased - max value is 100(no need to perform task to improve need)
     */
    public Need improveNeed(Need need, double amount);
}
