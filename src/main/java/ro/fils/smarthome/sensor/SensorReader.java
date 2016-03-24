/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.sensor;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ro.fils.smarthome.constants.Const;
import ro.fils.smarthome.constants.JSONReader_Constants;

/**
 *
 * @author Silvia
 */
public class SensorReader {

    private JSONObject object;

    public SensorReader(String fileName) throws Exception {
        JSONParser jp = new JSONParser();
        try {
            this.object = (JSONObject) jp.parse(new FileReader(fileName));
        } catch (Exception e) {
            this.object = null;
        }
    }

    public Collection<Sensor> getSensors() {
        Collection<Sensor> sensors = new ArrayList<>();
        if (object == null) {
            return sensors;
        } else {
            JSONArray sensorList = (JSONArray) object.get(JSONReader_Constants.Sensors.name());
            for (Object sensorOb : sensorList) {

                JSONObject sensorObject = (JSONObject) sensorOb;
                Sensor sensor = null;
                String type = (String) sensorObject.get(JSONReader_Constants.Type.name());
                String name = (String) sensorObject.get(JSONReader_Constants.Name.name());
                JSONArray locationArray = (JSONArray) sensorObject.get(JSONReader_Constants.Position.name());

                Point location = null;
                if (locationArray != null) {
                    location = new Point(Integer.parseInt(locationArray.get(0).toString()), Integer.parseInt(locationArray.get(1).toString()));
                }

                switch (type) {
                    case Const.CONTACT:
                        String attached = (String) sensorObject.get(JSONReader_Constants.Attached_to.name());
                        sensor = new Contact(name, attached);
                        break;
                    case Const.CAMERA:
                        JSONArray range = (JSONArray) sensorObject.get(JSONReader_Constants.Range.name());
                        double[] dArray = new double[range.size()];
                        for (int i = 0; i < dArray.length; i++) {
                            dArray[i] = Double.parseDouble(range.get(i).toString());
                        }
                        if (!sensorObject.containsKey(JSONReader_Constants.Resolution.name())) {
                            sensor = new Camera(name,
                                    location, Double.parseDouble(sensorObject.get(JSONReader_Constants.Direction.name()).toString()),
                                    Double.parseDouble(sensorObject.get(JSONReader_Constants.FieldOfView.name()).toString()),
                                    dArray);
                        } else {
                            sensor = new Camera(name,
                                    location, Double.parseDouble(sensorObject.get(JSONReader_Constants.Direction.name()).toString()),
                                    Double.parseDouble(sensorObject.get(JSONReader_Constants.FieldOfView.name()).toString()),
                                    dArray, Integer.parseInt(sensorObject.get(JSONReader_Constants.Resolution.name()).toString()));
                        }
                        break;
                    case Const.DOOR:
                        JSONArray dimensionArray = (JSONArray) sensorObject.get(JSONReader_Constants.Size.name());
                        Dimension dims = new Dimension(Integer.parseInt(dimensionArray.get(0).toString()),
                                Integer.parseInt(dimensionArray.get(1).toString()));
                        sensor = new Door(name, location, dims);
                        break;
                    case Const.MOTION_SENSOR:
                        if (sensorObject.containsKey(JSONReader_Constants.Radius.name())) {
                            sensor
                                    = new MotionSensor(name, location,
                                            Double.parseDouble(sensorObject.get(JSONReader_Constants.Radius.name()).toString()));
                        } else {
                            sensor = new MotionSensor(name, location,
                                    Double.parseDouble(sensorObject.get(JSONReader_Constants.Direction.name()).toString()),
                                    Double.parseDouble(sensorObject.get(JSONReader_Constants.Range.name()).toString()),
                                    Double.parseDouble(sensorObject.get(JSONReader_Constants.FieldOfView.name()).toString()));
                        }
                }

                if (sensorObject.containsKey(JSONReader_Constants.Exclude.name())) {
                    JSONArray excludeArray = (JSONArray) sensorObject.get(JSONReader_Constants.Exclude.name());
                    for (Object exclude : excludeArray) {
                        JSONObject exclud = (JSONObject) exclude;
                        JSONArray edgeArray = (JSONArray) exclud.get(JSONReader_Constants.Edge.name());
                        Point edgeLocation = new Point(Integer.parseInt(edgeArray.get(0).toString()),
                                Integer.parseInt(edgeArray.get(1).toString()));
                        String direction = (String) exclud.get(JSONReader_Constants.Direction.name());
                        Point edge = null;
                        Dimension dim = new Dimension(1000, 1000);
                        switch (direction) {
                            case (Const.NE):
                                edge = new Point(edgeLocation.x, edgeLocation.y - 1000);
                                break;
                            case (Const.NV):
                                edge = new Point(edgeLocation.x - 1000, edgeLocation.y - 1000);
                                break;
                            case (Const.SE):
                                edge = new Point(edgeLocation.x, edgeLocation.y);
                                break;
                            case (Const.SV):
                                edge = new Point(edgeLocation.x - 1000, edgeLocation.y);
                        }
                        Rectangle exluTangle = new Rectangle(edge, dim);
                        sensor.removeArea(new Area(exluTangle));
                    }
                }
                if (sensorObject.containsKey(JSONReader_Constants.Confine.name())) {
                    JSONArray coords = (JSONArray) sensorObject.get(JSONReader_Constants.Confine.name());
                    int[] x = new int[coords.size()];
                    int[] y = new int[coords.size()];
                    for (int i = 0; i < coords.size(); i++) {
                        JSONArray coord = (JSONArray) coords.get(i);
                        x[i] = Integer.parseInt(coord.get(0).toString());
                        y[i] = Integer.parseInt(coord.get(1).toString());
                    }
                    Polygon p = new Polygon(x, y, coords.size());
                    Area excluPoly = new Area(p);
                    sensor.confineToArea(excluPoly);
                }
                sensors.add(sensor);
            }

            return sensors;
        }

    }
}
