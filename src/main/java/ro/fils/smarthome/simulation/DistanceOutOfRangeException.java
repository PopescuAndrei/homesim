/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.simulation;

/**
 *
 * @author andre
 */
public class DistanceOutOfRangeException extends RuntimeException {

    public DistanceOutOfRangeException() {

    }

    public DistanceOutOfRangeException(String msg) {
        super(msg);
    }
}
