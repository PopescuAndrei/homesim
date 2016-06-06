/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util;

import java.util.ArrayList;
import java.util.List;
import ro.fils.smarthome.model.Need;

/**
 *
 * @author andre
 */
public class AgentUtil {
    
    public static List<Need> getNeeds(double energyValue, double hungerValue, double bladderValue, double hygieneValue, double funValue){
        List<Need> needs = new ArrayList<>();
        
        Need energy = new Need("Energy", energyValue);
        Need hunger = new Need("Hunger", hungerValue);
        Need bladder = new Need("Bladder", bladderValue);
        Need hygiene = new Need("Hygiene", hygieneValue);
        Need fun = new Need("Fun", funValue);
        needs.add(hunger);
        needs.add(bladder);
        needs.add(fun);
        needs.add(energy);
        needs.add(hygiene);
        return needs;
    }
}
