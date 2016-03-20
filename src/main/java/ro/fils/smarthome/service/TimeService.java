/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.service;

/**
 *
 * @author Silvia
 */
public interface TimeService {

    public int getSeconds(double currentTime);

    public String getNumberFormatted(int number);

    public int getMinutes(double currentTime);

    public int getHours(double currentTime);

    public int getDay(double currentTime);

    public int getDayOfWeek(double currentTime);

    public String getDayName(double currentTime);

    public int getWeek(double currentTime);
}
