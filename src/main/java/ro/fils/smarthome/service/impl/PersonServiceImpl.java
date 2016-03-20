/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service.impl;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fils.smarthome.model.Gadget;
import ro.fils.smarthome.model.ITask;
import ro.fils.smarthome.model.Item;
import ro.fils.smarthome.model.Need;
import ro.fils.smarthome.model.Person;
import ro.fils.smarthome.service.NeedService;
import ro.fils.smarthome.service.PersonService;

/**
 *
 * @author Silvia
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    NeedService needService;

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
    public Person setLocation(Person p, Point2D newLocation) {
        p.setCurrentLocation(newLocation);
        Deque<Node> route = p.getRoute();
        //TODO : uncomment when Node is replaced by our implementation
//        if ((route != null && !route.isEmpty())) {
//            if (p.getCurrentLocation().distance(route.peek().getLocation()) == 0) {
//                route.remove();
//                p.setRoute(route);
//            }
//        }
        return p;
    }

    @Override
    public Person setCurrentTask(ITask task, Person p) {
        p.setCurrentTask(task);
        if (task != null) {
            if (task.getType().equals("Automatic")) {
                p.setRemainingTaskDuration(60 * new Random().nextInt(3));
            } else {
                p.setRemainingTaskDuration(task.getDurationSeconds() + (60 * new Random().nextInt(4)));
            }
        }
        return p;
    }

    @Override
    public Person setCurrentTaskAndGadget(ITask task, Gadget gadget, Person p) {
        p.setCurrentTask(task);
        p.setGadget(gadget);
        return p;
    }

    @Override
    public Person progressTask(Person p, double seconds) {
        p.setRemainingTaskDuration(p.getRemainingTaskDuration() - seconds);
        return p;
    }

    @Override
    public Person setTargetItemAndFetchTime(Person p, String itemName) {
        p.setTargetItem(itemName);
        p.setFetchTime(2.0);
        return p;
    }

    @Override
    public Person progressFetch(double seconds, Person p) {
        p.setFetchTime(p.getFetchTime() - seconds);
        return p;
    }

    @Override
    public Person passTime(Person p, double seconds) {

        List<Need> needs = p.getNeeds();
        needs.stream().forEach((n) -> {
            needService.deteriorateNeed(n, seconds);
        });

        if (p.getPauseTime() > 0) {
            p.setPauseTime(p.getPauseTime() - seconds);
        }

        return p;
    }

    @Override
    public Person removeItemFromInventory(Person p, String itemName, int amount) {
        HashMap<String, Integer> inventory = p.getInventory();
        inventory.remove(itemName);
        p.setInventory(inventory);
        return p;
    }

    @Override
    public Set<String> getPoseData(Person p) {
        Set<String> poses = new HashSet<>();
        if (p.getCurrentTask() != null) {
            poses.addAll(p.getCurrentTask().getPoses());
        }
        if (p.getGadget() != null) {
            // TODO: uncomment after Gadget is implemented
//           poses.addAll(p.getGadget().getPoses()); 
        }

        Set<String> filtered = new HashSet<>();

        poses.stream().filter((pose) -> (!pose.trim().isEmpty())).forEach((pose) -> {
            filtered.add(pose);
        });
        return filtered;
    }

    @Override
    public boolean isMoving(Person p) {
        return (p.getRoute() != null && p.getRoute().size() > 0);
    }

    @Override
    public Person addState(Person p, String stateString) {
        Set<String> state = p.getState();

        if (stateString.charAt(0) == '+') {
            state.add(stateString.substring(1));
        } else {
            state.remove(stateString.substring(1));
        }
        return p;
    }

}
