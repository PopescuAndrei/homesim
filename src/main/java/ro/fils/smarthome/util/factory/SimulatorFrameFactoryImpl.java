/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util.factory;

import java.util.List;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.model.Scenario;
import ro.fils.smarthome.repository.AgentRepository;
import ro.fils.smarthome.view.SimulatorFrame;

/**
 *
 * @author andre
 */
public class SimulatorFrameFactoryImpl implements SimulatorFrameFactory {

    public SimulatorFrameFactoryImpl() {
    }

    @Override
    public SimulatorFrame getFrameForScenario(Scenario s) {
        return this.buildSimulatorFrame(s);
    }

    protected SimulatorFrame buildSimulatorFrame(Scenario s) {
        List<Agent> agents = new AgentRepository().getAgentsForScenario(s.getId());
        return new SimulatorFrame(s.getTaskile(), s.getSensorFile(), s.getHouseFile(), s.getWalking_speed(), s.getStartingPoint(), agents, s.getDays());
    }
}