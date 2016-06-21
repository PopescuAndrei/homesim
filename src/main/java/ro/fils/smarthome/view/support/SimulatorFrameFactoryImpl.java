/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view.support;

import java.util.List;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.model.Scenario;
import ro.fils.smarthome.repository.AgentRepository;
import ro.fils.smarthome.repository.HouseRepository;
import ro.fils.smarthome.view.SimulatorFrame;

/**
 *
 * @author andre
 */
public class SimulatorFrameFactoryImpl implements SimulatorFrameFactory {
    
    HouseRepository houseRepo = new HouseRepository();
    
    public SimulatorFrameFactoryImpl() {
    }

    @Override
    public SimulatorFrame getFrameForScenario(Scenario s) {
        return this.buildSimulatorFrame(s);
    }

    protected SimulatorFrame buildSimulatorFrame(Scenario s) {
        List<Agent> agents = new AgentRepository().getAgentsForScenario(s.getId());
        SimulatorFrame frame = new SimulatorFrame(s.getTaskile(), s.getSensorFile(), houseRepo.getHouseFileByHouseId(s.getHouseId()), s.getWalking_speed(), s.getStartingPoint(), agents, s.getSimsPerSec());
        frame.setScenarioName(s.getName());
        return frame;
    }
}
