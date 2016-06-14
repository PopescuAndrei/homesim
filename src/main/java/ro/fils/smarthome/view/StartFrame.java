/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ro.fils.smarthome.view.support.SimulatorFrameFactoryImpl;
import ro.fils.smarthome.view.support.SimulatorFrameFactory;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ro.fils.smarthome.model.Scenario;
import ro.fils.smarthome.repository.ScenarioRepository;
import ro.fils.smarthome.view.support.Observer;

/**
 *
 * @author andre
 */
public class StartFrame extends javax.swing.JFrame implements Observer {

    private final SimulatorFrameFactory factory;
    private DefaultListModel<String> scenariosModel;
    private String[] scenariosNames;
    private List<Scenario> scenarios;

    public StartFrame() {
        initComponents();
        this.setTitle("Welcome to the Smart Home Simulator");
        factory = new SimulatorFrameFactoryImpl();
        scenarios = new ScenarioRepository().getAllScenarios();
        scenariosNames = new String[scenarios.size()];

        for (int i = 0; i < scenarios.size(); i++) {
            scenariosNames[i] = scenarios.get(i).getName();
        }

        scenariosModel = new DefaultListModel();
        for (String s : scenariosNames) {
            scenariosModel.addElement(s);
        }
        listViewSimulation.setModel(scenariosModel);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnEditMap = new javax.swing.JButton();
        btnRunSimulation = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listViewSimulation = new javax.swing.JList<>();
        btnNewScenario = new javax.swing.JButton();
        btnAnalytics = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 188, 212));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Smarthome Simulator");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );

        btnEditMap.setText("Edit Map");
        btnEditMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMapActionPerformed(evt);
            }
        });

        btnRunSimulation.setText("Run Simulation");
        btnRunSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunSimulationActionPerformed(evt);
            }
        });

        listViewSimulation.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listViewSimulation);

        btnNewScenario.setText("New Scenario");
        btnNewScenario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewScenarioActionPerformed(evt);
            }
        });

        btnAnalytics.setText("Analytics");
        btnAnalytics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalyticsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNewScenario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditMap, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnalytics, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRunSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditMap)
                    .addComponent(btnRunSimulation)
                    .addComponent(btnNewScenario)
                    .addComponent(btnAnalytics))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRunSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunSimulationActionPerformed
        SimulatorFrame frame = factory.getFrameForScenario(scenarios.get(listViewSimulation.getSelectedIndex()));
        frame.setSize(1366, 768);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.stopSimulation();
                frame.dispose();
            }
        });
        frame.setVisible(true);
    }//GEN-LAST:event_btnRunSimulationActionPerformed

    private void btnEditMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMapActionPerformed
        DesignFrame frame = new DesignFrame();
        frame.setSize(1366, 768);
        frame.setVisible(true);
    }//GEN-LAST:event_btnEditMapActionPerformed

    private void btnNewScenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewScenarioActionPerformed
        ScenarioCreatorFrame frame = new ScenarioCreatorFrame();
        frame.addObserver(this);
        frame.setSize(1366, 768);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (frame.getAgentsDisplayList().isEmpty()) {
                    int reply = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to quit?\nYou won't be able to run the scenario if it has no agents!", "Quit", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        frame.dispose();
                    }
                }else{
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }//GEN-LAST:event_btnNewScenarioActionPerformed

    private void btnAnalyticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalyticsActionPerformed
        AnalyticsFrame frame = new AnalyticsFrame();
        frame.setSize(1366, 768);
        frame.setVisible(true);
    }//GEN-LAST:event_btnAnalyticsActionPerformed

    public void triggerRefresh() {
        scenarios = new ScenarioRepository().getAllScenarios();
        scenariosNames = new String[scenarios.size()];

        for (int i = 0; i < scenarios.size(); i++) {
            scenariosNames[i] = scenarios.get(i).getName();
        }

        scenariosModel = new DefaultListModel();
        for (String s : scenariosNames) {
            scenariosModel.addElement(s);
        }
        listViewSimulation.setModel(scenariosModel);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalytics;
    private javax.swing.JButton btnEditMap;
    private javax.swing.JButton btnNewScenario;
    private javax.swing.JButton btnRunSimulation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listViewSimulation;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        triggerRefresh();
    }
}
