/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.sensor;

import java.awt.Point;

/**
 *
 * @author andre
 */
class Circle extends Cone{
    
    public Circle(Point origo, double radian){
        super(origo, 0, new double[]{radian}, 360);
    }
}