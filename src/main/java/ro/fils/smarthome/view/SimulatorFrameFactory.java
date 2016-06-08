/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view;

import ro.fils.smarthome.model.Scenario;
import ro.fils.smarthome.view.SimulatorFrame;

/**
 *
 * @author andre
 */
public interface SimulatorFrameFactory {
    
    SimulatorFrame getFrameForScenario(Scenario scenario);
}