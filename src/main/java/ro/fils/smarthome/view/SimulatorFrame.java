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
import java.util.function.Function;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.model.Need;
import ro.fils.smarthome.sensor.Sensor;
import ro.fils.smarthome.sensor.SensorReader;
import ro.fils.smarthome.simulation.SimulationMap;
import ro.fils.smarthome.simulation.Simulator;
import ro.fils.smarthome.tasksManagement.TaskReader;
import ro.fils.smarthome.util.SensorLogger;
import ro.fils.smarthome.util.Time;

/**
 *
 * @author Silvia
 */
public class SimulatorFrame extends javax.swing.JFrame {

    private SimulationDisplay display;
    private SimulationMap simulationMap;
    private ClassPathXmlApplicationContext context;
    private Simulator simulator;
    private Timer timer;
    private long startTime;
    private int days;

    /**
     * Creates new form SimulatorFrame
     */
    public SimulatorFrame(ClassPathXmlApplicationContext ctx) {
        this.context = ctx;
        initSimulatorTools();
        initComponents();
        setTitle("Simulator");
    }

    private void initSimulatorTools() {
        try {
            Collection<Sensor> sensors = null;
            ArrayList<Agent> people = new ArrayList<>();
            TaskReader taskReader = new TaskReader();
            sensors = new SensorReader("/sensors.json").getSensors();
            simulationMap = new SimulationMap(context, "/environment.jpg", 50, 2L, people, 43, sensors);
            people.add(new Agent("Person1", "/agent.png", 0, simulationMap.getStartingPoint(), taskReader.getNeeds()));
            people.add(new Agent("Person2", "/agent.png", 0, simulationMap.getStartingPoint(), taskReader.getNeeds()));
            this.days = 30;

        } catch (Exception e) {
            Logger.getLogger(SimulatorFrame.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void populateAgentsAndNeeds(String agentName) {
        Collection<Agent> allAgents = simulationMap.getPeople();
        allAgents.stream().filter((person) -> (agentName.equals(person.getName()))).forEach((person) -> {
            List<Need> personNeeds = person.getNeeds();
        });
    }

    private String[] getAgents() {
        Collection<Agent> people = simulationMap.getPeople();
        String[] agents = new String[people.size()];
        int i = 0;
        for (Agent agent : people) {
            agents[i] = agent.getName();
            i++;
        }
        return agents;
    }

    public void setProgressBars(List<Need> personNeeds, Agent person) {
        for (Need need : personNeeds) {
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

        }
    }
    /**
     * 
     */    
    public void updateMenu() {
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        rightPanel = new javax.swing.JPanel();
        labelLogbook = new javax.swing.JLabel();
        scrollPaneLog = new javax.swing.JScrollPane();
        insideSplitPane = new javax.swing.JSplitPane();
        panelAgents = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listAgents = new javax.swing.JList<>();
        btnStartSimulation = new javax.swing.JButton();
        panelNeeds = new javax.swing.JPanel();
        labelDuration = new javax.swing.JLabel();
        editDurationDays = new javax.swing.JTextField();
        labelTimeDisplayed = new javax.swing.JLabel();
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
        leftPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setResizeWeight(0.3);

        labelLogbook.setText("Logbook:");
        Font font = labelLogbook.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        labelLogbook.setFont(boldFont);

        scrollPaneLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        insideSplitPane.setResizeWeight(0.4);

        jLabel4.setText("List of Agents:");

        listAgents.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        listAgents.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = getAgents();
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listAgents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listAgentsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listAgents);

        btnStartSimulation.setText("Start");
        btnStartSimulation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStartSimulationMouseClicked(evt);
            }
        });
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnStartSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelAgentsLayout.setVerticalGroup(
            panelAgentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(btnStartSimulation)
                .addContainerGap())
        );

        insideSplitPane.setLeftComponent(panelAgents);

        panelNeeds.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelNeeds.setAlignmentX(0.2F);
        panelNeeds.setAlignmentY(0.2F);
        panelNeeds.setAutoscrolls(true);

        labelDuration.setText("Duration in Days:");

        editDurationDays.setText("30");

        labelTimeDisplayed.setText("Time - to be displayed");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                        .addComponent(editDurationDays, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTimeDisplayed)
                        .addGap(0, 45, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelNeedsLayout.setVerticalGroup(
            panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNeedsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDuration)
                    .addComponent(editDurationDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTimeDisplayed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelProgressbars, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        insideSplitPane.setRightComponent(panelNeeds);

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(insideSplitPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(scrollPaneLog)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogbook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addComponent(insideSplitPane, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGap(0, 160, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
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
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
        );

        ActionListener timeListen = new ActionListener() {
            int slowSpeed = 10;
            int fastSpeed = 1;

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!simulator.simulationStep()) timer.setDelay(fastSpeed);
                else{
                    timer.setDelay(slowSpeed);
                    display.update(simulationMap.getPeople(), simulationMap.getSensors());
                    updateMenu();
                }
                if(Time.getDay(simulator.currentTime) == days + 1){
                    timer.stop();
                    System.out.println("Elapsed time in milliseconds: " + (System.currentTimeMillis() - startTime));
                }
            }
        };
        timer = new Timer(10, timeListen);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartSimulationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartSimulationMouseClicked
        
    }//GEN-LAST:event_btnStartSimulationMouseClicked

    private void listAgentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listAgentsMouseClicked
        String selectedValue = listAgents.getSelectedValue();
        populateAgentsAndNeeds(selectedValue);
    }//GEN-LAST:event_listAgentsMouseClicked

    private void btnStartSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartSimulationActionPerformed
//        try {
//            //simulator.setSensorLogger(new SensorLogger("sensorvals"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            Logger.getLogger(SimulatorFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
        days = Integer.parseInt(editDurationDays.getText());
        startTime = System.currentTimeMillis();
        timer.start();
    }//GEN-LAST:event_btnStartSimulationActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartSimulation;
    private javax.swing.JTextField editDurationDays;
    private javax.swing.JSplitPane insideSplitPane;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel labelBladder;
    private javax.swing.JLabel labelDuration;
    private javax.swing.JLabel labelEnergy;
    private javax.swing.JLabel labelFun;
    private javax.swing.JLabel labelHunger;
    private javax.swing.JLabel labelHygiene;
    private javax.swing.JLabel labelLogbook;
    private javax.swing.JLabel labelTimeDisplayed;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JList<String> listAgents;
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
