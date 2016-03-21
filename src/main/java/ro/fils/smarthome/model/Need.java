/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.model;

/**
 *
 * @author Silvia
 */
public class Need implements Comparable<Need> {

    private String name;
    private double decayRate;
    private double value;

    public Need(String name, double decayRate) {
        this.name = name;
        this.decayRate = decayRate;
        this.value = 100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDecayRate() {
        return decayRate;
    }

    public void setDecayRate(double decayRate) {
        this.decayRate = decayRate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Need need) {
        return (int) (this.value - need.value);
    }

    public void deteriorateNeed(double seconds) {

        this.value -= this.decayRate / (60 * 60 * seconds);
        if (this.value < 0) {
            this.value = 0;
        }
    }

    public void improveNeed(int amount) {

        this.value = this.value + amount;
        if (this.value > 100) {
            this.value = 100;
        }

    }

    @Override
    public String toString() {
        return "Need{" + "name=" + name + ", decayRate=" + decayRate + ", value=" + value + '}';
    }

}
