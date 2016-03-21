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
public class Time {

    private double startTime;

    /**
     *
     * @param startTime - Current time in seconds
     */
    public Time(double startTime) {
        this.startTime = startTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public int getSeconds(double currentTime) {
        return (int) (currentTime) % 60;
    }

    public String getNumberFormatted(int number) {
        return (number < 10 ? "0" + number : "" + number);
    }

    public int getMinutes(double currentTime) {
        return (int) (currentTime / 60) % 60;
    }

    public int getHours(double currentTime) {
        return (int) (currentTime / (60 * 60)) % 24;
    }

    public int getDay(double currentTime) {
        return 1 + (int) (currentTime / (60 * 60 * 24));
    }

    public int getDayOfWeek(double currentTime) {
        return 1 + (int) (currentTime / (60 * 60 * 24)) % 7;
    }

    public String getDayName(double currentTime) {
        String dayName = "";
        int day = getDay(currentTime);
        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            case 7:
                dayName = "Sunday";
                break;
        }
        return dayName;
    }

    public int getWeek(double currentTime) {
        return 1 + ((int) currentTime / (60 * 60 * 24 * 7));
    }

}
