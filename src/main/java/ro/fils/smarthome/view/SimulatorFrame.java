/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view;

import ro.fils.smarthome.view.support.SimulationDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.util.support.BoolWithLog;
import ro.fils.smarthome.model.Need;
import ro.fils.smarthome.sensors.Sensor;
import ro.fils.smarthome.parsers.SensorReader;
import ro.fils.smarthome.simulation.SimulationMap;
import ro.fils.smarthome.simulation.Simulator;
import ro.fils.smarthome.util.TaskManager;
import ro.fils.smarthome.parsers.TaskReader;
import ro.fils.smarthome.repository.HouseRepository;
import ro.fils.smarthome.util.SensorLogger;
import ro.fils.smarthome.util.support.Time;

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
    private int simsPerSec;
    private Collection<Sensor> sensors;
    private ArrayList<Agent> people;
    private TaskReader taskReader;
    private DefaultListModel<String> agentsListModel;
    private String selectedAgentName;
    private String scenarioName;

    public SimulatorFrame(String taskFile, String sensorsFile, String houseFile, int walkingSpeed, Long startingPoint, List<Agent> agents, int simsPerSec) {
        this.setTitle("Test your house");
        this.selectedAgentName = agents.get(0).getName();
        this.simsPerSec = simsPerSec;

        initSimulatorTools(taskFile, sensorsFile, houseFile, walkingSpeed, startingPoint, agents, simsPerSec);

        initComponents();
        agentsListModel = new DefaultListModel();
        people.forEach(p -> agentsListModel.addElement(p.getName()));
        listViewAgents.setModel(agentsListModel);

        listViewAgents.addListSelectionListener((ListSelectionEvent lse) -> {
            selectedAgentName = listViewAgents.getSelectedValue();
            populateAgentsAndNeeds(selectedAgentName);
        });

        timer = new Timer(10, timeListen);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        rightPanel = new javax.swing.JPanel();
        scrollPaneLog = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();
        panelTitle = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        panelLogTitle = new javax.swing.JPanel();
        labelLogActivities = new javax.swing.JLabel();
        panelAgents = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listViewAgents = new javax.swing.JList<>();
        btnStartSimulation = new javax.swing.JButton();
        btnStopSimulation = new javax.swing.JButton();
        panelNeeds = new javax.swing.JPanel();
        labelDuration = new javax.swing.JLabel();
        editDuration = new javax.swing.JTextField();
        labelTime = new javax.swing.JLabel();
        panelProgressbars = new javax.swing.JPanel();
        labelHunger = new javax.swing.JLabel();
        labelEnergy = new javax.swing.JLabel();
        labelBladder = new javax.swing.JLabel();
        labelFun = new javax.swing.JLabel();
        labelHygiene = new javax.swing.JLabel();
        progressHunger = new javax.swing.JProgressBar(0,100);
        progressEnergy = new javax.swing.JProgressBar(0,100);
        progressBladder = new javax.swing.JProgressBar(0,100);
        progressFun = new javax.swing.JProgressBar(0,100);
        progressHygiene = new javax.swing.JProgressBar(0,100);
        labelDaysLong = new javax.swing.JLabel();
        labelTimeIs = new javax.swing.JLabel();
        leftPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(490);
        jSplitPane1.setResizeWeight(0.3);

        scrollPaneLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        logArea.setColumns(20);
        logArea.setRows(5);
        scrollPaneLog.setViewportView(logArea);

        panelTitle.setBackground(new java.awt.Color(0, 188, 212));

        labelTitle.setBackground(new java.awt.Color(255, 255, 255));
        labelTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(255, 255, 255));
        labelTitle.setText("Simulation Display");

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        panelLogTitle.setBackground(new java.awt.Color(0, 188, 212));

        labelLogActivities.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelLogActivities.setForeground(new java.awt.Color(255, 255, 255));
        labelLogActivities.setText("Agents Activity Log:");

        javax.swing.GroupLayout panelLogTitleLayout = new javax.swing.GroupLayout(panelLogTitle);
        panelLogTitle.setLayout(panelLogTitleLayout);
        panelLogTitleLayout.setHorizontalGroup(
            panelLogTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogActivities, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLogTitleLayout.setVerticalGroup(
            panelLogTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelLogActivities, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        panelAgents.setBackground(new java.awt.Color(250, 250, 250));

        jLabel4.setBackground(new java.awt.Color(0, 188, 212));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 188, 212));
        jLabel4.setText("Agents List:");

        listViewAgents.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listViewAgents);

        btnStartSimulation.setText("Start Simulation");
        btnStartSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartSimulationActionPerformed(evt);
            }
        });

        btnStopSimulation.setText("Stop Simulation");
        btnStopSimulation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStopSimulationMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelAgentsLayout = new javax.swing.GroupLayout(panelAgents);
        panelAgents.setLayout(panelAgentsLayout);
        panelAgentsLayout.setHorizontalGroup(
            panelAgentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAgentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgentsLayout.createSequentialGroup()
                        .addGroup(panelAgentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnStopSimulation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(btnStartSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelAgentsLayout.setVerticalGroup(
            panelAgentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgentsLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStartSimulation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStopSimulation)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        panelNeeds.setBackground(new java.awt.Color(250, 250, 250));
        panelNeeds.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agent's Needs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 188, 212))); // NOI18N
        panelNeeds.setAlignmentX(0.2F);
        panelNeeds.setAlignmentY(0.2F);
        panelNeeds.setAutoscrolls(true);

        labelDuration.setText("Simulation Runtime");

        editDuration.setText("30");

        labelTime.setText("Time - to be displayed");

        panelProgressbars.setBackground(new java.awt.Color(250, 250, 250));

        labelHunger.setText("Hunger");

        labelEnergy.setText("Energy");

        labelBladder.setText("Bladder");

        labelFun.setText("Fun");

        labelHygiene.setText("Hygiene");

        progressHunger.setForeground(new java.awt.Color(0, 188, 212));

        progressEnergy.setForeground(new java.awt.Color(0, 188, 212));

        progressBladder.setForeground(new java.awt.Color(0, 188, 212));

        progressFun.setForeground(new java.awt.Color(0, 188, 212));

        progressHygiene.setForeground(new java.awt.Color(0, 188, 212));

        javax.swing.GroupLayout panelProgressbarsLayout = new javax.swing.GroupLayout(panelProgressbars);
        panelProgressbars.setLayout(panelProgressbarsLayout);
        panelProgressbarsLayout.setHorizontalGroup(
            panelProgressbarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProgressbarsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProgressbarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelHygiene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelHunger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelBladder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelProgressbarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressHygiene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBladder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressHunger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelProgressbarsLayout.setVerticalGroup(
            panelProgressbarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProgressbarsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProgressbarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHunger)
                    .addComponent(progressHunger, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelProgressbarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressEnergy, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEnergy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelProgressbarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBladder, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBladder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelProgressbarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressFun, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProgressbarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressHygiene, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHygiene))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        labelDaysLong.setText("days long.");

        labelTimeIs.setText("Time is :");

        javax.swing.GroupLayout panelNeedsLayout = new javax.swing.GroupLayout(panelNeeds);
        panelNeeds.setLayout(panelNeedsLayout);
        panelNeedsLayout.setHorizontalGroup(
            panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNeedsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelProgressbars, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelNeedsLayout.createSequentialGroup()
                        .addComponent(labelDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDaysLong, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                    .addGroup(panelNeedsLayout.createSequentialGroup()
                        .addComponent(labelTimeIs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelNeedsLayout.setVerticalGroup(
            panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNeedsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelProgressbars, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDuration)
                    .addComponent(editDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDaysLong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTimeIs)
                    .addComponent(labelTime))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneLog)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addComponent(panelAgents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNeeds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelLogTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelNeeds, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelAgents, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(panelLogTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneLog, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane1.setRightComponent(rightPanel);

        leftPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        leftPanel.setAlignmentY(0.3F);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        try {
            display = new SimulationDisplay("/environment.jpg");
            display.update(simulationMap.getPeople(), simulationMap.getSensors());
            leftPanel.add(display);

        }catch(Exception e){
            e.printStackTrace();
        }

        jSplitPane1.setLeftComponent(leftPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartSimulationActionPerformed

        try {
            Calendar cal = Calendar.getInstance();
            Date time = cal.getTime();
            String timeOfSimulation = time.toString().replaceAll("\\s", "_").replaceAll(":", "-");
            simulator.setSensorLogger(new SensorLogger(this.scenarioName + "_" + timeOfSimulation + ".txt"));
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(SimulatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        days = Integer.parseInt(editDuration.getText());
        startTime = System.currentTimeMillis();
        timer.start();
    }//GEN-LAST:event_btnStartSimulationActionPerformed

    private void btnStopSimulationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStopSimulationMouseClicked
        stopSimulation();
    }//GEN-LAST:event_btnStopSimulationMouseClicked

    private void initSimulatorTools(String taskFile, String sensorsFile, String houseFile, int walkingSpeed, Long startNodeId, List<Agent> agents, int simsPerSec) {
        try {
            this.people = (ArrayList<Agent>) agents;
            this.taskReader = new TaskReader(taskFile);
            sensors = new SensorReader(sensorsFile).getSensors();
            simulationMap = new SimulationMap(new HouseRepository().getHouseByFileName(houseFile), walkingSpeed, startNodeId, people, walkingSpeed, sensors);
            simulator = new Simulator(simulationMap, new TaskManager(taskReader), 3);
            this.days = 7;

        } catch (Exception e) {
            Logger.getLogger(SimulatorFrame.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateMenu() {
        if (listViewAgents.getSelectedValue() == null) {
            populateAgentsAndNeeds(selectedAgentName);
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
        int slowSpeed = 20;
        int fastSpeed = 1;

        @Override
        public void actionPerformed(ActionEvent ae) {
            BoolWithLog result = simulator.simulationStep();
            if (!result.isMovement()) {
            } else {
                display.update(simulationMap.getPeople(), simulationMap.getSensors());
                updateMenu();
            }
            if (Time.getDay(simulator.currentTime) == days + 1) {
                timer.stop();
                System.out.println("Elapsed time in milliseconds: " + (System.currentTimeMillis() - startTime));
            }

            if (result.getLog() != null) {
                logArea.append("\n" + result.getLog());
            }
            updateMenu();
        }
    };

    public void stopSimulation() {
        timer.stop();
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartSimulation;
    private javax.swing.JButton btnStopSimulation;
    private javax.swing.JTextField editDuration;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel labelBladder;
    private javax.swing.JLabel labelDaysLong;
    private javax.swing.JLabel labelDuration;
    private javax.swing.JLabel labelEnergy;
    private javax.swing.JLabel labelFun;
    private javax.swing.JLabel labelHunger;
    private javax.swing.JLabel labelHygiene;
    private javax.swing.JLabel labelLogActivities;
    private javax.swing.JLabel labelTime;
    private javax.swing.JLabel labelTimeIs;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JList<String> listViewAgents;
    private javax.swing.JTextArea logArea;
    private javax.swing.JPanel panelAgents;
    private javax.swing.JPanel panelLogTitle;
    private javax.swing.JPanel panelNeeds;
    private javax.swing.JPanel panelProgressbars;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JProgressBar progressBladder;
    private javax.swing.JProgressBar progressEnergy;
    private javax.swing.JProgressBar progressFun;
    private javax.swing.JProgressBar progressHunger;
    private javax.swing.JProgressBar progressHygiene;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JScrollPane scrollPaneLog;
    // End of variables declaration//GEN-END:variables
}
