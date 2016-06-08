/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util.support;

/**
 *
 * @author andre
 */
public class BoolWithLog {
    
    private boolean movement;
    private String log;

    public BoolWithLog(boolean movement, String log) {
        this.movement = movement;
        this.log = log;
    }
    
    

    public boolean isMovement() {
        return movement;
    }

    public void setMovement(boolean movement) {
        this.movement = movement;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
    
    
}
