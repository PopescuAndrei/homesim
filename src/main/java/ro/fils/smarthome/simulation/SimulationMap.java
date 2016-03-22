/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import ro.fils.smarthome.model.Gadget;
import ro.fils.smarthome.model.ITask;
import ro.fils.smarthome.model.Item;
import ro.fils.smarthome.model.Node;
import ro.fils.smarthome.model.Person;
import ro.fils.smarthome.service.NodeService;
import ro.fils.smarthome.util.Const;

/**
 *
 * @author andre
 */
public class SimulationMap {

    private final int walkingSpeedPerSec;
    private final String mapName;
    private final Long startNodeId;
    private final int dotsPerMeter;

    private ArrayList<Node> nodes;
    private final Collection<Person> people;
    private ArrayList<Gadget> objects;
    private Collection<Item> items;
    //TODO private Collection<Sensor> sensors;
    private final Node startNode;
    private Collection<AutoTask> runningTasks;

    @Autowired
    private NodeService nodeService;

    public SimulationMap(String mapName, int walkingSpeed, Long startNodeId, Collection<Person> people, int dotsPerMeter) {
        this.mapName = mapName;
        this.people = people;
        //this.sensors = sensors
        this.walkingSpeedPerSec = walkingSpeed;
        this.dotsPerMeter = dotsPerMeter;
        this.startNodeId = startNodeId;

        this.startNode = nodeService.findNodeById(startNodeId);
        this.items = new ArrayList<>();
        this.runningTasks = new ArrayList<>();
    }

    public Node getClosesNode(Point start) {
        double smallestDistance = Const.MAX_DISTANCE;
        Node smallestNode = null;
        for (Node node : nodes) {
            double distance = node.distance(start);
            if (distance < smallestDistance) {
                smallestNode = node;
            }
        }
        return smallestNode;
    }

    public Collection<Person> getPeople() {
        return people;
    }

    public Point moveActor(Person person, int simulationsPerSec) {
        int walkingSpeed = (int) (walkingSpeedPerSec / simulationsPerSec);
        Point p = new Point((int) person.getCurrentLocation().getX(), (int) person.getCurrentLocation().getY());
        Node targetNode = person.getRoute().peek();
        Point targetLocation = targetNode.getLocation();
        double distance = p.distance(targetLocation);
        int dx = (int) (targetLocation.getX() - p.getX());
        int dy = (int) (targetLocation.getY() - p.getY());
        if (distance < walkingSpeed) {
            //p.setLocation
        }
        return null;
    }

    public void addItem(Item item) {
        System.out.println("Added " + item.getName() + " to map");
        this.items.add(item);
    }

    public Item popItem(String itemName, Node location) {
        for (Item item : items) {
            if (Objects.equals(location.getId(), item.getLocation().getId()) && item.getName().equals(itemName)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    public boolean hasItem(String requestedItem, int amount) {
        int count = 0;
        count = items.stream()
                .filter((item) -> (item.getName().equals(requestedItem)))
                .map((_item) -> 1)
                .reduce(count, Integer::sum);
        return count >= amount;
    }

    public Collection<Node> getLocationOfGadgets(Collection<String> gadgetTypes) {
        Collection<Node> locations = new ArrayList<>();
        objects.stream().forEach((g) -> {
            gadgetTypes.stream().filter((gType) -> (g.getType().equals(gType))).forEach((_item) -> {
                locations.add(g.getNode());
            });
        });
        return locations;
    }

    public Collection<Gadget> getGadgets() {
        return objects;
    }

    public Node getRandomNode() {
        List<Node> nodePool = new ArrayList<>(nodes);
        if (nodePool.isEmpty()) {
            return null;
        }
        objects.stream().forEach((gadget) -> {
            nodePool.remove(gadget.getNode());
        });
        Random rand = new Random();
        return nodePool.get(rand.nextInt(nodePool.size()));
    }

    public Point getStartingPoint() {
        return startNode == null ? new Point(0, 0) : startNode.getLocation();
    }

    void addTask(ITask currentTask, Node closestNode) {
        this.runningTasks.add(new AutoTask(currentTask, closestNode));
    }

    void progressTasks(double seconds) {
        Iterator<AutoTask> it = runningTasks.iterator();
        while (it.hasNext()) {
            AutoTask t = it.next();
            t.progressTask(seconds);
            if (t.getDuration() <= 0.0) {
                //System.out.println(t.getTask().getName() + ", " + t.getTask().getCreatedItems());
//                for(String item: t.getTask().getCreatedItems()){
//                    this.addItem(new Item(item, t.getNode()));
//                }
                it.remove();
                //System.out.println(t.getTask().getName() + " completed");
            }
        }
    }

    private static class AutoTask {

        private final ITask task;
        private final Node node;
        private double duration;

        AutoTask(ITask currentTask, Node closestNode) {
            this.task = currentTask;
            this.node = closestNode;
            this.duration = currentTask.getDurationSeconds();
        }

        void progressTask(double seconds) {
            duration = duration - seconds;
        }

        double getDuration() {
            return duration;
        }

        ITask getTask() {
            return task;
        }

        Node getNode() {
            return node;
        }
    }
}
