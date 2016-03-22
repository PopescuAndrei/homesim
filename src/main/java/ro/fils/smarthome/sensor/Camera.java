/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.sensor;

import java.awt.Point;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author andre
 */
public class Camera implements Sensor{
    
    private final String name;
    private final Point position;
    private final List<SensorArea> areas;
    
    public Camera(String name, Point position, double directionDegrees, double fieldOfView, double[] range, int partitions){
        //Cone cone = new Cone(position, directionDegrees, range, fieldOfView);
        this.name = name;
        this.position = position;
        areas = getSegments(position, directionDegrees, fieldOfView, range, partitions);
    }
    
    public Camera(String name, Point position, double directionDegrees, double fieldOfView, double[] range){
        //Cone cone = new Cone(position, directionDegrees, range, fieldOfView);
        this.name = name;
        this.position = position;
        areas = getSegments(position, directionDegrees, fieldOfView, range, 5);
    }
    
    private List<SensorArea> getSegments(Point position, double direction, double fov, double[] range, int partitions){
        ArrayList<SensorArea> segments = new ArrayList<>();
        double rangeIncrement = (range[1] - range[0]) / partitions;
        double leftDirection = direction - (fov/2);
        double degreeIncrement = fov / partitions;
        leftDirection += degreeIncrement / 2;
        for(int i = 0; i < partitions; i++){
            for(int j = 0; j < partitions; j++){
                double[] newRange = new double[2];
                newRange[0] = range[0] + (rangeIncrement * j);
                newRange[1] = range[0] + (rangeIncrement * (j + 1));
                segments.add(new SensorArea(name + i + j, 
                        new Area(new Cone(position, leftDirection + (degreeIncrement * i), newRange, fov / partitions).getShape())));
            }
        }
        return segments;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<SensorArea> getSensorAreas() {
        return areas;
    }

    @Override
    public void removeArea(Area area) {
        Iterator<SensorArea> it = areas.iterator();
        while (it.hasNext()){
            SensorArea sa = it.next();
            sa.getArea().subtract(area);
            if(sa.getArea().isEmpty()) it.remove();
        }
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void confineToArea(Area area) {
        Iterator<SensorArea> it = areas.iterator();
        while (it.hasNext()){
            SensorArea sa = it.next();
            sa.getArea().intersect(area);
            if(sa.getArea().isEmpty()) it.remove();
        }
    }
}
