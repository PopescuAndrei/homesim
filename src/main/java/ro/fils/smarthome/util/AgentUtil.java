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
        energy.setId(1);
        Need hunger = new Need("Hunger", hungerValue);
        hunger.setId(2);
        Need bladder = new Need("Bladder", bladderValue);
        bladder.setId(3);
        Need hygiene = new Need("Hygiene", hygieneValue);
        hygiene.setId(4);
        Need fun = new Need("Fun", funValue);
        fun.setId(5);
        needs.add(hunger);
        needs.add(bladder);
        needs.add(fun);
        needs.add(energy);
        needs.add(hygiene);
        return needs;
    }
}
