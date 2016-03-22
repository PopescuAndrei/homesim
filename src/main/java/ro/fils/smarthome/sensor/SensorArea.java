/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.sensor;

import java.awt.geom.Area;


/**
 *
 * @author andre
 */
public class SensorArea {

    private String name;
    private Area area;
    private String lastValue;

    public SensorArea(String name, Area area) {
        this.name = name;
        this.area = area;
        this.lastValue = "";
    }

    public SensorArea() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getLastValue() {
        return lastValue;
    }

    public void setLastValue(String lastValue) {
        this.lastValue = lastValue;
    }

}
