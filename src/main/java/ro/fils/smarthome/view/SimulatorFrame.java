/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.model.Need;
import ro.fils.smarthome.sensor.Sensor;
import ro.fils.smarthome.sensor.SensorReader;
import ro.fils.smarthome.simulation.SimulationMap;
import ro.fils.smarthome.simulation.Simulator;
import ro.fils.smarthome.tasksManagement.TaskManager;
import ro.fils.smarthome.tasksManagement.TaskReader;
import ro.fils.smarthome.util.SensorLogger;
import ro.fils.smarthome.util.Time;

/**
 *
 * @author andre
 */
public class SimulatorFrame extends javax.swing.JFrame {

    private SimulationDisplay display;
    private SimulationMap simulationMap;
    private Simulator simulator;
    private Timer timer;
    private long startTime;
    private int days;
    private Collection<Sensor> sensors;
    private ArrayList<Agent> people;
    private TaskReader taskReader;
    private DefaultListModel<String> agentsListModel;

    public SimulatorFrame() {
        initSimulatorTools();
        initComponents();

        agentsListModel = new DefaultListModel();
        people.forEach(p -> agentsListModel.addElement(p.getName()));
        listViewAgents.setModel(agentsListModel);

        listViewAgents.addListSelectionListener((ListSelectionEvent lse) -> {
            populateAgentsAndNeeds(listViewAgents.getSelectedValue());
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        panelLog = new javax.swing.JPanel();
        labelLog = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelAgents = new javax.swing.JPanel();
        labelAgentsList = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listViewAgents = new javax.swing.JList<>();
        btnStartSimulation = new javax.swing.JButton();
        panelNeeds = new javax.swing.JPanel();
        panelProgressBars = new javax.swing.JPanel();
        labelHunger = new javax.swing.JLabel();
        labelEnergy = new javax.swing.JLabel();
        labelBladder = new javax.swing.JLabel();
        labelFun = new javax.swing.JLabel();
        labelHygiene = new javax.swing.JLabel();
        progressHunger = new javax.swing.JProgressBar();
        progressEnergy = new javax.swing.JProgressBar();
        progressBladder = new javax.swing.JProgressBar();
        progressFun = new javax.swing.JProgressBar();
        progressHygiene = new javax.swing.JProgressBar();
        labelPersonNeeds = new javax.swing.JLabel();
        labelTimeIs = new javax.swing.JLabel();
        editDuration = new javax.swing.JTextField();
        labelDaysLong = new javax.swing.JLabel();
        labelSimulationTime = new javax.swing.JLabel();
        labelTime = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setResizeWeight(0.3);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(leftPanel);
        try {
            display = new SimulationDisplay("/environment.jpg");
            display.update(simulationMap.getPeople(), simulationMap.getSensors());
        }catch(Exception e){
            e.printStackTrace();
        }
        leftPanel.add(display);

        panelLog.setBackground(new java.awt.Color(200, 200, 200));

        labelLog.setText("Log");

        javax.swing.GroupLayout panelLogLayout = new javax.swing.GroupLayout(panelLog);
        panelLog.setLayout(panelLogLayout);
        panelLogLayout.setHorizontalGroup(
            panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(panelLogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLogLayout.setVerticalGroup(
            panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogLayout.createSequentialGroup()
                .addComponent(labelLog, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        labelAgentsList.setText("Agents List");

        listViewAgents.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listViewAgents);

        btnStartSimulation.setText("Start Simulation");
        btnStartSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartSimulationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAgentsLayout = new javax.swing.GroupLayout(panelAgents);
        panelAgents.setLayout(panelAgentsLayout);
        panelAgentsLayout.setHorizontalGroup(
            panelAgentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAgentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAgentsList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(btnStartSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAgentsLayout.setVerticalGroup(
            panelAgentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAgentsList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStartSimulation)
                .addContainerGap())
        );

        panelProgressBars.setBackground(new java.awt.Color(200, 200, 200));

        labelHunger.setText("Hunger");

        labelEnergy.setText("Energy");

        labelBladder.setText("Bladder");

        labelFun.setText("Fun");

        labelHygiene.setText("Hygiene");

        progressHunger.setMaximumSize(new java.awt.Dimension(32767, 20));
        progressHunger.setMinimumSize(new java.awt.Dimension(10, 20));
        progressHunger.setPreferredSize(new java.awt.Dimension(146, 20));

        progressEnergy.setMaximumSize(new java.awt.Dimension(32767, 20));
        progressEnergy.setMinimumSize(new java.awt.Dimension(10, 20));
        progressEnergy.setPreferredSize(new java.awt.Dimension(146, 20));

        progressBladder.setMaximumSize(new java.awt.Dimension(32767, 20));
        progressBladder.setMinimumSize(new java.awt.Dimension(10, 20));
        progressBladder.setPreferredSize(new java.awt.Dimension(146, 20));

        progressFun.setMaximumSize(new java.awt.Dimension(32767, 20));
        progressFun.setMinimumSize(new java.awt.Dimension(10, 20));
        progressFun.setPreferredSize(new java.awt.Dimension(146, 20));

        javax.swing.GroupLayout panelProgressBarsLayout = new javax.swing.GroupLayout(panelProgressBars);
        panelProgressBars.setLayout(panelProgressBarsLayout);
        panelProgressBarsLayout.setHorizontalGroup(
            panelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProgressBarsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelFun, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelBladder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelHygiene, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(labelHunger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressHunger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBladder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressHygiene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelProgressBarsLayout.setVerticalGroup(
            panelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProgressBarsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(progressHunger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelHunger, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressEnergy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(progressBladder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelBladder, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelHygiene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressHygiene, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelPersonNeeds.setText("Agent Needs");

        labelTimeIs.setText("Time is: ");

        editDuration.setText("30");

        labelDaysLong.setText("days long.");

        labelSimulationTime.setText("Simulation Run Time");

        labelTime.setText("Not Started");

        javax.swing.GroupLayout panelNeedsLayout = new javax.swing.GroupLayout(panelNeeds);
        panelNeeds.setLayout(panelNeedsLayout);
        panelNeedsLayout.setHorizontalGroup(
            panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNeedsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelProgressBars, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelPersonNeeds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelNeedsLayout.createSequentialGroup()
                        .addComponent(labelTimeIs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelNeedsLayout.createSequentialGroup()
                        .addComponent(labelSimulationTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDaysLong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelNeedsLayout.setVerticalGroup(
            panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNeedsLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(labelPersonNeeds)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelProgressBars, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTimeIs)
                    .addComponent(labelTime))
                .addGap(11, 11, 11)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSimulationTime)
                    .addComponent(editDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDaysLong))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addComponent(panelAgents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNeeds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAgents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNeeds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane1.setRightComponent(rightPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initSimulatorTools() {
        timer = new Timer(10, timeListen);
        try {
            people = new ArrayList<>();
            taskReader = new TaskReader("/activities.json");
            sensors = new SensorReader("/sensors.json").getSensors();
            simulationMap = new SimulationMap("/environment.jpg", 50, 1L, people, 43, sensors);
            people.add(new Agent("Roby Roberto", "/boy_avatar_48p.png", simulationMap.getStartingPoint(), taskReader.getNeeds()));
            simulator = new Simulator(simulationMap, new TaskManager(taskReader), 3);
            this.days = 30;

        } catch (Exception e) {
            Logger.getLogger(SimulatorFrame.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void btnStartSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartSimulationActionPerformed
        try {
            simulator.setSensorLogger(new SensorLogger("sensorvals"));
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(SimulatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        days = Integer.parseInt(editDuration.getText());
        startTime = System.currentTimeMillis();
        timer.start();
    }//GEN-LAST:event_btnStartSimulationActionPerformed

    public void updateMenu() {
        if (listViewAgents.getSelectedValue() == null) {
            populateAgentsAndNeeds("");
        } else {
            populateAgentsAndNeeds(listViewAgents.getSelectedValue());
        }
        labelTime.setText("W: " + simulator.time.getWeek(simulator.currentTime)
                + ", D: " + simulator.time.getDayName(simulator.currentTime)
                + ", " + simulator.time.getNumberFormatted(simulator.time.getHours(simulator.currentTime))
                + ":" + simulator.time.getNumberFormatted(simulator.time.getMinutes(simulator.currentTime))
                + ":" + simulator.time.getNumberFormatted(simulator.time.getSeconds(simulator.currentTime)));
    }

    private void populateAgentsAndNeeds(String agentName) {
        Collection<Agent> allAgents = simulationMap.getPeople();
        for (Agent person : allAgents) {
            if (agentName.equals(person.getName())) {
                List<Need> personNeeds = person.getNeeds();
                setProgressBars(personNeeds, person);
            }
        }
    }

    public void setProgressBars(List<Need> personNeeds, Agent person) {
        personNeeds.stream().forEach((need) -> {
            if (need.getName().equalsIgnoreCase("hunger")) {
                progressHunger.setName(person.getName() + "," + need.getName());
                progressHunger.setValue((int) need.getValue());
                progressHunger.setString("" + (int) need.getValue());
                progressHunger.setStringPainted(true);
            } else if (need.getName().equalsIgnoreCase("energy")) {
                progressEnergy.setName(person.getName() + "," + need.getName());
                progressEnergy.setValue((int) need.getValue());
                progressEnergy.setString("" + (int) need.getValue());
                progressEnergy.setStringPainted(true);
            } else if (need.getName().equalsIgnoreCase("bladder")) {
                progressBladder.setName(person.getName() + "," + need.getName());
                progressBladder.setValue((int) need.getValue());
                progressBladder.setString("" + (int) need.getValue());
                progressBladder.setStringPainted(true);
            } else if (need.getName().equalsIgnoreCase("fun")) {
                progressFun.setName(person.getName() + "," + need.getName());
                progressFun.setValue((int) need.getValue());
                progressFun.setString("" + (int) need.getValue());
                progressFun.setStringPainted(true);
            } else if (need.getName().equalsIgnoreCase("hygiene")) {
                progressHygiene.setName(person.getName() + "," + need.getName());
                progressHygiene.setValue((int) need.getValue());
                progressHygiene.setString("" + (int) need.getValue());
                progressHygiene.setStringPainted(true);
            }
        });
    }

    ActionListener timeListen = new ActionListener() {
        int slowSpeed = 10;
        int fastSpeed = 1;

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!simulator.simulationStep()) {
                timer.setDelay(fastSpeed);
            } else {
                timer.setDelay(slowSpeed);
                display.update(simulationMap.getPeople(), simulationMap.getSensors());
                updateMenu();
            }
            if (Time.getDay(simulator.currentTime) == days + 1) {
                timer.stop();
                System.out.println("Elapsed time in milliseconds: " + (System.currentTimeMillis() - startTime));
            }
        }
    };
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartSimulation;
    private javax.swing.JTextField editDuration;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel labelAgentsList;
    private javax.swing.JLabel labelBladder;
    private javax.swing.JLabel labelDaysLong;
    private javax.swing.JLabel labelEnergy;
    private javax.swing.JLabel labelFun;
    private javax.swing.JLabel labelHunger;
    private javax.swing.JLabel labelHygiene;
    private javax.swing.JLabel labelLog;
    private javax.swing.JLabel labelPersonNeeds;
    private javax.swing.JLabel labelSimulationTime;
    private javax.swing.JLabel labelTime;
    private javax.swing.JLabel labelTimeIs;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JList<String> listViewAgents;
    private javax.swing.JPanel panelAgents;
    private javax.swing.JPanel panelLog;
    private javax.swing.JPanel panelNeeds;
    private javax.swing.JPanel panelProgressBars;
    private javax.swing.JProgressBar progressBladder;
    private javax.swing.JProgressBar progressEnergy;
    private javax.swing.JProgressBar progressFun;
    private javax.swing.JProgressBar progressHunger;
    private javax.swing.JProgressBar progressHygiene;
    private javax.swing.JPanel rightPanel;
    // End of variables declaration//GEN-END:variables
}
