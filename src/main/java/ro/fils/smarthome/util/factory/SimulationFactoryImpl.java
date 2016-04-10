/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.sensor.Sensor;
import ro.fils.smarthome.sensor.SensorReader;
import ro.fils.smarthome.tasksManagement.TaskReader;
import ro.fils.smarthome.view.SimulationDisplay;

/**
 *
 * @author andre
 */
public class SimulationFactoryImpl implements SimulationFactory {

    private ArrayList<Agent> people;
    private TaskReader taskReader;
    private Collection<Sensor> sensors;
    private int days;

    public SimulationFactoryImpl() {
    }

    @Override
    public SimulationDisplay getFactory(String simulationName) {
//        if(simulationName.equalsIgnoreCase("Old Man")){
              setElementsForScenarioOldMan();
//        }else{
              setElementsForScenarioBoyAndGirl();
//        }
//        return new SimulationDisplay(ctx,sensors,people, taskReader.getNeeds()));
          return null;
    }

    protected void setElementsForScenarioOldMan() {
        try {
            sensors = new SensorReader("/sensors.json").getSensors();
            //simulationMap = new SimulationMap(context, "/environment.jpg", 50, 2L, people, 43, sensors);
            //people.add(new Agent("Person1", "/agent.png", 0, simulationMap.getStartingPoint(), taskReader.getNeeds("old_man_needs.json")));
            //people.add(new Agent("Boy", "/boy_avatar_48p.png", 0, simulationMap.getStartingPoint(), taskReader.getNeeds("/boy_needs.json")));
            this.days = 30;
        } catch (Exception ex) {
            Logger.getLogger(SimulationFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void setElementsForScenarioBoyAndGirl(){
        try {
            sensors = new SensorReader("/sensors2.json").getSensors();
            //simulationMap = new SimulationMap(context, "/environment.jpg", 50, 2L, people, 43, sensors);
            //people.add(new Agent("Person1", "/agent.png", 0, simulationMap.getStartingPoint(), taskReader.getNeeds("old_man_needs.json")));
            //people.add(new Agent("Boy", "/boy_avatar_48p.png", 0, simulationMap.getStartingPoint(), taskReader.getNeeds("/boy_needs.json")));
            this.days = 30;
        } catch (Exception ex) {
            Logger.getLogger(SimulationFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

