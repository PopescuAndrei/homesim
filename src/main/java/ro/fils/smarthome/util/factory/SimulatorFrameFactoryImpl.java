/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util.factory;

import ro.fils.smarthome.view.SimulatorFrame;

/**
 *
 * @author andre
 */
public class SimulatorFrameFactoryImpl implements SimulatorFrameFactory {

    public SimulatorFrameFactoryImpl() {
    }

    @Override
    public SimulatorFrame getFrameForScenario(String scenarioName) {
        SimulatorFrame frame;
        if (scenarioName.equalsIgnoreCase("Old Man")) {
            frame = setElementsForScenarioOldMan();
        } else if(scenarioName.equalsIgnoreCase("Normal Speed")){
            frame = setElementsForScenarioGirlWalkingMedium();
        } else{
            frame = setElementsForScenarioNormal();
        }
        return frame;
    }

    protected SimulatorFrame setElementsForScenarioNormal() {
        String taskFile = "/activities.json";
        String sensorsFile = "/sensors.json";
        String houseFile = "/environment.jpg";
        int walkingSpeed = 30;
        Long startingPoint = 1L;
        String agentName = "Will Hunting";
        String agentPicFile = "/running.gif";
        int days = 30;
        return new SimulatorFrame(taskFile, sensorsFile, houseFile, walkingSpeed, startingPoint, agentName, agentPicFile, days);
    }
    
    protected SimulatorFrame setElementsForScenarioGirlWalkingMedium(){
        String taskFile = "/activities.json";
        String sensorsFile = "/sensors.json";
        String houseFile = "/environment.jpg";
        int walkingSpeed = 30;
        Long startingPoint = 1L;
        String agentName = "Danela";
        String agentPicFile = "/girl_avatar_48p.png";
        int days = 30;
        return new SimulatorFrame(taskFile, sensorsFile, houseFile, walkingSpeed, startingPoint, agentName, agentPicFile, days);
    }

    protected SimulatorFrame setElementsForScenarioOldMan(){
        String taskFile = "/activities.json";
        String sensorsFile = "/sensors.json";
        String houseFile = "/environment.jpg";
        int walkingSpeed = 10;
        Long startingPoint = 1L;
        String agentName = "Robert Deniro";
        String agentPicFile = "/grandpa.gif";
        int days = 30;
        return new SimulatorFrame(taskFile, sensorsFile, houseFile, walkingSpeed, startingPoint, agentName, agentPicFile, days);
    }
}
