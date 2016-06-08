/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import ro.fils.smarthome.tasks.ITask;
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
    
    
    public static <Task extends ITask> Deque<Task> getPlan(SimulationMap m, Agent p, Collection<Task> tasks, Task goalTask) throws Exception{
        PLPlan plan = new PLPlan();
        plan.setAlgorithm(EnumAlgorithm.GRAPHPLAN);
        Collection<String> requiredFacts = getFacts(goalTask);
        for(String f: requiredFacts){
            plan.addGoalFact(f);
        }
        Collection<String> currentFacts = getState(m, p);
        for(String s: currentFacts){
            plan.addFact(s);
        }
        for(Task t: tasks){
            addTask(t, plan);
        }
        List planList = plan.findPlan();
        Deque<Task> taskPlan = new ArrayDeque<>();
        for(Object ob: planList){
            List pl = null;
            if(ob instanceof ArrayList){
                pl = (List) ob;
                for(Object obs: pl){
                    for(Task t: tasks){
                        if(obs.toString().equals(t.getName())) taskPlan.add(t);
                    }
                }
            }
        }
        
        return taskPlan;
    }
    
    private static Collection<String> getFacts(ITask goalTask){
        Collection<String> reqFacts = new ArrayList<>();
        Map<String, Integer> requiredItems = goalTask.getRequiredItems();
        for(String fact: requiredItems.keySet()){
            reqFacts.add(fact);
        }
        reqFacts.addAll(goalTask.getPrecond());
        return reqFacts;
    }
    
    private static Collection<String> getState(SimulationMap m, Agent p){
        Collection<Item> items = m.getItems();
        Collection<String> state = new ArrayList<>();
        Map<String, Integer> inventory = p.getInventory();
        for(Item item: items){
            state.add(item.getName());
        }
        for(String s: inventory.keySet()){
            state.add(s);
        }
        state.addAll(p.getState());
        return state;
    }

    private static void addTask(ITask t, PLPlan plan) {
        List<String> precond = new ArrayList<>();
        List<String> pos = new ArrayList<>();
        List<String> neg = new ArrayList<>();
        Map<String, Integer> k = t.getRequiredItems();
        precond.addAll(t.getPrecond());
        pos.addAll(t.getPos());
        neg.addAll(t.getNeg());
        
        for(String s: k.keySet()){
            precond.add(s);
            neg.add(s);
        }
        Set<String> created = t.getCreatedItems();
        for(String s: created){
            pos.add(s);
        }
        plan.addOperator(t.getName(), precond, neg, pos);
        //System.out.println(t.name() + ", P" + precond.toString() + ", -" + neg.toString() + ", +" + pos.toString());
    }
}