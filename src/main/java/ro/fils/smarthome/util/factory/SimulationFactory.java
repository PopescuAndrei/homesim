/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util.factory;

import ro.fils.smarthome.view.SimulationDisplay;

/**
 *
 * @author andre
 */
public interface SimulationFactory {
    
    SimulationDisplay getFactory(String simulationName);
}