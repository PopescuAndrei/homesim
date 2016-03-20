/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service;

import java.util.Set;
import javafx.geometry.Point2D;
import ro.fils.smarthome.model.Gadget;
import ro.fils.smarthome.model.ITask;
import ro.fils.smarthome.model.Item;
import ro.fils.smarthome.model.Need;
import ro.fils.smarthome.model.Person;

/**
 *
 * @author Silvia
 */
public interface PersonService {

    public Person addNeed(Person p, Need n);

    public Person addItemToInventory(Person p, Item item);

    public boolean hasItem(Person p, String item, int amount);

    public Person setLocation(Person p, Point2D newLocation);

    public Person setCurrentTask(ITask task, Person p);

    public Person setCurrentTaskAndGadget(ITask task, Gadget gadget, Person p);

    public Person progressTask(Person p, double seconds);

    public Person setTargetItemAndFetchTime(Person p, String itemName);

    public Person progressFetch(double seconds, Person p);
    
    public Person passTime(Person p, double seconds);
    
    public Person removeItemFromInventory(Person p, String itemName, int amount);
    
    public Set<String> getPoseData(Person p);
    
    public boolean isMoving(Person p);
    
    public Person addState(Person p, String stateString);
    
    

}
