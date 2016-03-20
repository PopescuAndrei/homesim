/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

import java.util.Set;

/**
 *
 * @author Silvia
 */
public interface ITask {

    public String fulfilledNeed();

    public boolean available(double time);

    public String getType();

    public double getDurationSeconds();

    public Set<String> getPoses();

//    TODO: to be added methods
}
