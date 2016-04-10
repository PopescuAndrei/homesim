/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.planManagement;

import java.util.ArrayDeque;
import java.util.ArrayList;
import ro.fils.smarthome.tasksManagement.ITask;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;
import plplan.javaapi.EnumAlgorithm;
import plplan.javaapi.PLPlan;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.model.Item;
import ro.fils.smarthome.simulation.SimulationMap;

/**
 *
 * @author Silvia
 */
public class PPlanWrapper {

    public static <Task extends ITask> Deque<Task> getPlan(SimulationMap m, Agent p, Collection<Task> tasks, Task goalTask) throws Exception {
        PLPlan plan = new PLPlan();
        plan.setAlgorithm(EnumAlgorithm.GRAPHPLAN);
        Collection<String> requiredFacts = getFacts(goalTask);

        requiredFacts.stream().forEach((fact) -> {
            plan.addGoalFact(fact);
        });

        Collection<String> currentFacts = getState(m, p);
        currentFacts.stream().forEach((s) -> {
            plan.addFact(s);
        });

        tasks.stream().forEach((t) -> {
            addTask(t, plan);
        });

        List planList = plan.findPlan();
        Deque<Task> taskPlan = new ArrayDeque<>();
        for (Object ob : planList) {
            if (ob instanceof ArrayList) {
                List pl = (List) ob;
                for (Object obs : pl) {
                    for (Task t : tasks) {
                        if (obs.toString().equals(t.getName())) {
                            taskPlan.add(t);
                        }
                    }
                }
            }
        }

        return taskPlan;
    }

    private static <Task extends Object & ITask> Collection<String> getFacts(Task goalTask) {
        Collection<String> reqFacts = new ArrayList<>();
        Map<String, Integer> requiredItems = goalTask.getRequiredItems();
        requiredItems.keySet().stream().forEach((fact) -> {
            reqFacts.add(fact);
        });
        reqFacts.addAll(goalTask.getPrecond());
        return reqFacts;
    }

    private static Collection<String> getState(SimulationMap m, Agent p) {
        Collection<Item> items = m.getItems();
        Collection<String> state = new ArrayList<>();
        Map<String, Integer> inventory = p.getInventory();
        items.stream().forEach((item) -> {
            state.add(item.getName());
        });
        inventory.keySet().stream().forEach((s) -> {
            state.add(s);
            System.out.println("State " + s + " added");
        });
        state.addAll(p.getState());
        return state;
    }

    private static <Task extends Object & ITask> void addTask(Task t, PLPlan plan) {
        List<String> precond = new ArrayList<>();
        List<String> pos = new ArrayList<>();
        List<String> neg = new ArrayList<>();

        Map<String, Integer> k = t.getRequiredItems();
        precond.addAll(t.getPrecond());

        pos.addAll(t.getPos());
        neg.addAll(t.getNeg());

        for (String s : k.keySet()) {
            precond.add(s);
            neg.add(s);
        }

        Set<String> created = t.getCreatedItems();
        created.stream().forEach((s) -> {
            //Logger.egtLogger(PPlanWrapper.class.getName()).log(Level.SEVERE, null, "?????????" + s);
            pos.add(s);
        });
        plan.addOperator(t.getName(), precond, neg, pos);
    }

}
