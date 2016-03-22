/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.sensor;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class Door implements Sensor {

    private final String name;
    private final Point location;
    private final Area area;
    private final SensorArea sa;

    public Door(String name, Point location, Dimension dimensions) {
        this.name = name;
        this.location = location;
        this.area = new Area(new Rectangle(location, dimensions));
        this.sa = new SensorArea(name, area);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<SensorArea> getSensorAreas() {
        List<SensorArea> areas = new ArrayList<>();
        areas.add(sa);
        return areas;
    }

    @Override
    public void removeArea(Area area) {
        this.area.subtract(area);
    }

    @Override
    public Point getPosition() {
        return location;
    }

    @Override
    public void confineToArea(Area area) {
        this.area.intersect(area);
    }

}
