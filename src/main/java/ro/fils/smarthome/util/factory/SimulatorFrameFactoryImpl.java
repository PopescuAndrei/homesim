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
        } else if (scenarioName.equalsIgnoreCase("Normal Speed")) {
            frame = setElementsForScenarioGirlWalkingMedium();
        } else if (scenarioName.equalsIgnoreCase("Normal Speed Scenario Set 2")) {
            frame = setElementsForScenarioNormalActivitiesSet2();
        } else {
            frame = setElementsForScenarioNormal();
        }
        return frame;
    }

    protected SimulatorFrame setElementsForScenarioNormal() {
        String scenario = "scenario_Normal";
        String taskFile = "/activities.json";
        String sensorsFile = "/sensors.json";
        String houseFile = "/environment.jpg";
        int walkingSpeed = 30;
        Long startingPoint = 1L;
        String agentName = "Will Hunting";
        String agentPicFile = "/running.gif";
        int days = 30;
        return new SimulatorFrame(taskFile, sensorsFile, houseFile, walkingSpeed, startingPoint, agentName, agentPicFile, days, scenario);
    }

    protected SimulatorFrame setElementsForScenarioGirlWalkingMedium() {
        String scenario = "scenario_GirlWalkingMedium";
        String taskFile = "/activities.json";
        String sensorsFile = "/sensors.json";
        String houseFile = "/environment.jpg";
        int walkingSpeed = 30;
        Long startingPoint = 1L;
        String agentName = "Danela";
        String agentPicFile = "/girl_avatar_48p.png";
        int days = 30;
        return new SimulatorFrame(taskFile, sensorsFile, houseFile, walkingSpeed, startingPoint, agentName, agentPicFile, days, scenario);
    }

    protected SimulatorFrame setElementsForScenarioOldMan() {
        String scenario = "scenario_OldMan";
        String taskFile = "/activities.json";
        String sensorsFile = "/sensors.json";
        String houseFile = "/environment.jpg";
        int walkingSpeed = 10;
        Long startingPoint = 1L;
        String agentName = "Robert Deniro";
        String agentPicFile = "/grandpa.gif";
        int days = 30;
        return new SimulatorFrame(taskFile, sensorsFile, houseFile, walkingSpeed, startingPoint, agentName, agentPicFile, days, scenario);
    }

    protected SimulatorFrame setElementsForScenarioNormalActivitiesSet2() {
        String scenario = "scenario_NormalActivitiesSet2";
        String taskFile = "/activitiesSet2.json";
        String sensorsFile = "/sensors.json";
        String houseFile = "/environment.jpg";
        int walkingSpeed = 30;
        Long startingPoint = 1L;
        String agentName = "Will Hunting";
        String agentPicFile = "/running.gif";
        int days = 30;
        return new SimulatorFrame(taskFile, sensorsFile, houseFile, walkingSpeed, startingPoint, agentName, agentPicFile, days, scenario);
    }
}
