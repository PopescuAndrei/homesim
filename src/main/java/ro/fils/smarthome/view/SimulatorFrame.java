/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.util.BoolWithLog;
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
    private String selectedAgentName;
    
    public SimulatorFrame(String taskFile, String sensorsFile, String houseFile, int walkingSpeed, Long startingPoint, String agentName, String agentPicFile, int days) {
        this.selectedAgentName = agentName;
        
        initSimulatorTools(taskFile, sensorsFile, houseFile, walkingSpeed, startingPoint, agentName, agentPicFile, days);
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
        labelLogbook = new javax.swing.JLabel();
        scrollPaneLog = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();
        insidePanel = new javax.swing.JPanel();
        panelAgents = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listViewAgents = new javax.swing.JList<>();
        btnStartSimulation = new javax.swing.JButton();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        leftPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setResizeWeight(0.3);

        labelLogbook.setText("Log:");
        Font font = labelLogbook.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        labelLogbook.setFont(boldFont);

        scrollPaneLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        logArea.setColumns(20);
        logArea.setRows(5);
        scrollPaneLog.setViewportView(logArea);

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
                            .addComponent(btnStartSimulation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );
        panelAgentsLayout.setVerticalGroup(
            panelAgentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStartSimulation)
                .addGap(6, 6, 6))
        );

        panelNeeds.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelNeeds.setAlignmentX(0.2F);
        panelNeeds.setAlignmentY(0.2F);
        panelNeeds.setAutoscrolls(true);

        labelDuration.setText("Simulation Runtime");

        editDuration.setText("30");

        labelTime.setText("Time - to be displayed");

        panelProgressbars.setBackground(new java.awt.Color(210, 210, 210));

        labelHunger.setText("Hunger");

        labelEnergy.setText("Energy");

        labelBladder.setText("Bladder");

        labelFun.setText("Fun");

        labelHygiene.setText("Hygiene");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(labelHygiene)))
        );

        jLabel1.setText("days long.");

        jLabel2.setText("Time is :");

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
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelNeedsLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelNeedsLayout.setVerticalGroup(
            panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNeedsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelProgressbars, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDuration)
                    .addComponent(editDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelTime))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout insidePanelLayout = new javax.swing.GroupLayout(insidePanel);
        insidePanel.setLayout(insidePanelLayout);
        insidePanelLayout.setHorizontalGroup(
            insidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, insidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAgents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNeeds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        insidePanelLayout.setVerticalGroup(
            insidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAgents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelNeeds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneLog, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogbook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(insidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addComponent(insidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLogbook)
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
            .addGap(0, 298, Short.MAX_VALUE)
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
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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
            simulator.setSensorLogger(new SensorLogger("sensorvals"));
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(SimulatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        days = Integer.parseInt(editDuration.getText());
        startTime = System.currentTimeMillis();
        timer.start();
    }//GEN-LAST:event_btnStartSimulationActionPerformed

    private void initSimulatorTools(String taskFile, String sensorsFile, String houseFile, int walkingSpeed, Long startingPoint, String agentName, String agentPicFile, int days) {
        try {
            this.people = new ArrayList<>();
            this.taskReader = new TaskReader(taskFile);
            sensors = new SensorReader(sensorsFile).getSensors();
            simulationMap = new SimulationMap(houseFile, walkingSpeed, startingPoint, people, 43, sensors);
            people.add(new Agent(agentName, agentPicFile, simulationMap.getStartingPoint(), taskReader.getNeeds()));
            simulator = new Simulator(simulationMap, new TaskManager(taskReader), 3);
            this.days = days;

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
            BoolWithLog result= simulator.simulationStep();
            if (!result.isMovement()) {
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
            if(result.getLog()!=null){
                logArea.append("\n" + result.getLog());
            }
            updateMenu();
        }
    };

     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartSimulation;
    private javax.swing.JTextField editDuration;
    private javax.swing.JPanel insidePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel labelBladder;
    private javax.swing.JLabel labelDuration;
    private javax.swing.JLabel labelEnergy;
    private javax.swing.JLabel labelFun;
    private javax.swing.JLabel labelHunger;
    private javax.swing.JLabel labelHygiene;
    private javax.swing.JLabel labelLogbook;
    private javax.swing.JLabel labelTime;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JList<String> listViewAgents;
    private javax.swing.JTextArea logArea;
    private javax.swing.JPanel panelAgents;
    private javax.swing.JPanel panelNeeds;
    private javax.swing.JPanel panelProgressbars;
    private javax.swing.JProgressBar progressBladder;
    private javax.swing.JProgressBar progressEnergy;
    private javax.swing.JProgressBar progressFun;
    private javax.swing.JProgressBar progressHunger;
    private javax.swing.JProgressBar progressHygiene;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JScrollPane scrollPaneLog;
    // End of variables declaration//GEN-END:variables
}
