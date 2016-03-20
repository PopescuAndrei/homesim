/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javafx.scene.effect.Light;
import org.springframework.stereotype.Service;
import ro.fils.smarthome.model.Gadget;
import ro.fils.smarthome.model.ITask;
import ro.fils.smarthome.model.Item;
import ro.fils.smarthome.model.Need;
import ro.fils.smarthome.model.Person;
import ro.fils.smarthome.service.PersonService;

/**
 *
 * @author Silvia
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public Person addNeed(Person p, Need n) {

        List<Need> needs = p.getNeeds();
        needs.add(n);
        p.setNeeds(needs);
        return p;
    }

    @Override
    public Person addItemToInventory(Person p, Item item) {

        HashMap<String, Integer> inventory = p.getInventory();
        inventory.put(item.getName(), (inventory.get(item.getName()) != null
                ? inventory.get(item.getName()) : 0) + 1);
        p.setInventory(inventory);
        return p;
    }

    @Override
    public boolean hasItem(Person p, String item, int amount) {
        HashMap<String, Integer> inventory = p.getInventory();
        return (inventory.containsKey(item) && inventory.get(item) >= amount);
    }

    @Override
    public Person setLocation(Person p, Light.Point newLocation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person setCurrentTask(ITask task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person setCurrentTaskAndGadget(ITask task, Gadget gadget) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person progressTask(Person p, double seconds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person setTargetItemAndFetchTime(Person p, String itemName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person progressFetch(double seconds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person passTime(Person p, double seconds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person removeItemFromInventory(Person p, String itemName, int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> getPoseData(Person p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isMoving(Person p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person addState(Person p, String stateString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
