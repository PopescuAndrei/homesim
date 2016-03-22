/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import ro.fils.smarthome.simulation.SimulationMap;

/**
 *
 * @author Silvia
 */
public interface ITask {

    public String fulfilledNeed();

    public boolean available(double time);

    boolean itemsExist(Agent p, SimulationMap map);

    public boolean agentMeetsRequirements(Agent agent);

    Collection<Appliance> getViableAppliances(Collection<Appliance> allGadges);

    public Map<String, Integer> getRequiredItems();

    public double getDurationSeconds();

    public Set<String> getCreatedItems();

    public Set<String> getRequiredItemsSet();

    public Collection<String> getUsedAppliances();

    void completeTask(Agent p, SimulationMap map);

    public void consumeItem(Agent p);

    public String getName();

    public Set<String> getPoses();

    public Set<String> getNeg();

    public Set<String> getPos();

    public Set<String> getPrecond();

    public String label();

    public String getType();

    boolean itemExists(Agent agent, SimulationMap map);

    public void recentlyCompleted();

    public void passTime(double d);

}
