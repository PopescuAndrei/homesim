/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.tasksManagement;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.fils.smarthome.constants.Const;
import ro.fils.smarthome.model.Need;
import ro.fils.smarthome.constants.JSONReader_Constants;

/**
 *
 * @author Silvia
 */
public class TaskReader {

    private JSONObject object;

    public TaskReader(String fileName) {
        JSONParser jp = new JSONParser();
        try {
            this.object = (JSONObject) jp.parse(new FileReader(getClass().getResource(fileName).getPath()));
        } catch (IOException | ParseException ex) {
            System.out.println("ro.fils.smarthome.util.TaskReader.<init>() => " + ex.getLocalizedMessage());
            this.object = null;
        }
    }

    public Collection<ITask> getTasks() {
        Collection<ITask> tasks = new ArrayList<>();

        if (object == null) {
            return tasks;
        } else {

            JSONArray taskList = (JSONArray) object.get(JSONReader_Constants.Activities.name());

            for (Iterator it = taskList.iterator(); it.hasNext();) {
                JSONObject task = (JSONObject) it.next();
                Task newTask;

                if (task.containsKey(JSONReader_Constants.Timespan.name())) {
                    JSONArray timespan = (JSONArray) task.get(JSONReader_Constants.Timespan.name());
                    newTask = new Task((String) task.get(JSONReader_Constants.Name.name()),
                            (String) task.get(JSONReader_Constants.Type.name()),
                            Integer.parseInt((String) task.get(JSONReader_Constants.Duration.name()).toString()),
                            Integer.parseInt(timespan.get(0).toString()),
                            Integer.parseInt(timespan.get(1).toString()),
                            (String) task.get(JSONReader_Constants.Appliance.name()),
                            (task.containsKey(JSONReader_Constants.Label.name()) ? (String) task.get(JSONReader_Constants.Label.name()) : null));
                    tasks.add(newTask);
                } else {
                    newTask = new Task((String) task.get(JSONReader_Constants.Name.name()),
                            (String) task.get(JSONReader_Constants.Type.name()),
                            ((Long) task.get(JSONReader_Constants.Duration.name())).intValue(),
                            (String) task.get(JSONReader_Constants.Appliance.name()),
                            (task.containsKey(JSONReader_Constants.Label.name()) ? (String) task.get(JSONReader_Constants.Label.name()) : null));
                    tasks.add(newTask);
                }

                if (task.containsKey(JSONReader_Constants.Cooldown.name())) {
                    double d = (long) task.get(JSONReader_Constants.Cooldown.name()) * 3600;
                    newTask.setCooldown(d);
                }

                if (task.containsKey(JSONReader_Constants.IncreasesNeed.name())) {
                    JSONArray need = (JSONArray) task.get(JSONReader_Constants.IncreasesNeed.name());
                    newTask.addResult((String) need.get(0), Integer.parseInt(need.get(1).toString()));
                }

                if (task.containsKey(JSONReader_Constants.RequiresItem.name())) {
                    if (!(task.get(JSONReader_Constants.RequiresItem.name()) instanceof JSONArray)) {
                        newTask.addRequiredItem((String) task.get(JSONReader_Constants.RequiresItem.name()), 1);
                    } else {
                        JSONArray requirements = (JSONArray) task.get(JSONReader_Constants.RequiresItem.name());
                        if (requirements.get(1) instanceof Number) {
                            newTask.addRequiredItem((String) requirements.get(0), Integer.parseInt(requirements.get(1).toString()));
                        } else {
                            requirements.stream().forEach((item) -> {
                                newTask.addRequiredItem((String) item, 1);
                            });
                        }
                    }
                }

                if (task.containsKey(JSONReader_Constants.Creates.name())) {
                    if (task.get(JSONReader_Constants.Creates.name()) instanceof JSONArray) {
                        JSONArray creates = (JSONArray) task.get(JSONReader_Constants.Creates.name());
                        newTask.addResultingItem((String) creates.get(0), Integer.parseInt(creates.get(1).toString()));
                    } else {
                        newTask.addResultingItem((String) task.get(JSONReader_Constants.Creates.name()), 1);
                    }
                }

                if (task.containsKey(JSONReader_Constants.PoseFeatures.name())) {
                    if (task.get(JSONReader_Constants.PoseFeatures.name()) instanceof JSONArray) {
                        JSONArray poses = (JSONArray) task.get(JSONReader_Constants.PoseFeatures.name());
                        poses.stream().map((pose) -> (String) pose).forEach((poseString) -> {
                            newTask.addPose((String) poseString);
                        });
                    }
                }

                if (task.containsKey(JSONReader_Constants.NeedsState.name())) {
                    if (task.get(JSONReader_Constants.NeedsState.name()) instanceof JSONArray) {
                        JSONArray poses = (JSONArray) task.get(JSONReader_Constants.NeedsState.name());
                        poses.stream().map((pose) -> (String) pose).forEach((poseString) -> {
                            newTask.addNeededState((String) poseString);
                        });
                    }
                }

                if (task.containsKey(JSONReader_Constants.AddsState.name())) {
                    if (task.get(JSONReader_Constants.AddsState.name()) instanceof JSONArray) {
                        JSONArray poses = (JSONArray) task.get(JSONReader_Constants.AddsState.name());
                        for (Object pose : poses) {

                            String poseString = (String) pose;
                            if (poseString.charAt(0) == Const.PLUS) {
                                newTask.addPlusState(poseString.substring(1));
                            }
                            if (poseString.charAt(0) == Const.MINUS) {
                                newTask.addMinusState(poseString.substring(1));
                            }
                        }
                    }
                }
            }

            return tasks;
        }
    }

    public Set<String> getAppliances() {
        Set<String> appliances = new HashSet<>();

        if (object == null) {
            return appliances;
        } else {

            JSONArray tasklist = (JSONArray) object.get(JSONReader_Constants.Activities.name());
            Iterator tasks = tasklist.iterator();
            while (tasks.hasNext()) {
                JSONObject task = (JSONObject) tasks.next();
                if (task.containsKey(JSONReader_Constants.Appliance.name())) {
                    appliances.add((String) task.get(JSONReader_Constants.Appliance.name()));
                }
                if (task.containsKey(JSONReader_Constants.Creates.name()) && task.get(JSONReader_Constants.Creates.name()) instanceof JSONArray) {
                    JSONArray createArray = (JSONArray) task.get(JSONReader_Constants.Creates.name());
                    if (createArray.get(1) instanceof String) {
                        appliances.add(createArray.get(1).toString());
                    }
                }
            }
            System.out.println(appliances.size() + " this much appliances");
            System.out.println();
            return appliances;
        }
    }
}
