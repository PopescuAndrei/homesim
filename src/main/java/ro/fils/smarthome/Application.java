package ro.fils.smarthome;

import ro.fils.smarthome.util.FilesUtils;
import ro.fils.smarthome.view.StartFrame;

public class Application{

    public static void main(String[] args) {
        StartFrame frame = new StartFrame();
        frame.setVisible(true);
        FilesUtils.parseLine("Sensor -BathCamera-: -Mosulica- is in position -(74.0, 502.0)-, -98- distance from the sensor, doing -Eat food-");
    }

}
