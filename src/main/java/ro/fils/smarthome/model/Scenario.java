/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

/**
 *
 * @author andre
 */
public class Scenario {
    
    private int id;
    private String name;
    private String taskile;
    private String sensorFile;
    private String houseFile;
    private Long startingPoint;
    private int days;
    private int walking_speed;

    public Scenario() {
    }

    
    public Scenario(int id, String name, String taskile, String sensorFile, String houseFile, Long startingPoint, int days, int walking_speed) {
        this.id = id;
        this.name = name;
        this.taskile = taskile;
        this.sensorFile = sensorFile;
        this.houseFile = houseFile;
        this.startingPoint = startingPoint;
        this.days = days;
        this.walking_speed = walking_speed;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskile() {
        return taskile;
    }

    public void setTaskile(String taskile) {
        this.taskile = taskile;
    }

    public String getSensorFile() {
        return sensorFile;
    }

    public void setSensorFile(String sensorFile) {
        this.sensorFile = sensorFile;
    }

    public String getHouseFile() {
        return houseFile;
    }

    public void setHouseFile(String houseFile) {
        this.houseFile = houseFile;
    }

    public Long getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Long startingPoint) {
        this.startingPoint = startingPoint;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getWalking_speed() {
        return walking_speed;
    }

    public void setWalking_speed(int walking_speed) {
        this.walking_speed = walking_speed;
    }
    
    
    
}
