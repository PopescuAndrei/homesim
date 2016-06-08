/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.model.Need;
import ro.fils.smarthome.model.Scenario;
import ro.fils.smarthome.repository.AgentRepository;
import ro.fils.smarthome.repository.NodeRepository;
import ro.fils.smarthome.repository.ScenarioRepository;
import ro.fils.smarthome.util.AgentUtil;

/**
 *
 * @author andre
 */
public class ScenarioCreatorFrame extends javax.swing.JFrame {

    private final AgentRepository agentRepo;
    private final ScenarioRepository scenarioRepo;
    private final NodeRepository nodeRepo;

    private Scenario scenarioToBeSaved;
    List<Agent> agentsDisplayList;
    DefaultListModel<String> agentsListModel;

    public ScenarioCreatorFrame() {
        initComponents();
        labelAvatar1.setHorizontalAlignment(JLabel.CENTER);
        labelAvatar2.setHorizontalAlignment(JLabel.CENTER);
        labelAvatar3.setHorizontalAlignment(JLabel.CENTER);
        labelAvatar4.setHorizontalAlignment(JLabel.CENTER);

        agentRepo = new AgentRepository();
        scenarioRepo = new ScenarioRepository();
        nodeRepo = new NodeRepository();

        agentsDisplayList = new ArrayList<>();
        agentsListModel = new DefaultListModel<>();
        listViewAgent.setModel(agentsListModel);

        sliderBladder.addChangeListener((ChangeEvent e) -> {
            valueBladder.setText(new Double(sliderBladder.getValue()) / 10 + "");
        });

        sliderHunger.addChangeListener((ChangeEvent e) -> {
            valueHunger.setText(new Double(sliderHunger.getValue()) / 10 + "");
        });

        sliderEnergy.addChangeListener((ChangeEvent e) -> {
            valueEnergy.setText(new Double(sliderEnergy.getValue()) / 10 + "");
        });

        sliderHygiene.addChangeListener((ChangeEvent e) -> {
            valueHygiene.setText(new Double(sliderHygiene.getValue()) / 10 + "");
        });

        sliderFun.addChangeListener((ChangeEvent e) -> {
            valueFun.setText(new Double(sliderFun.getValue()) / 10 + "");
        });

        disableFields();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupAvatar = new javax.swing.ButtonGroup();
        jSplitPane1 = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        labelHomeSchemeFile = new javax.swing.JLabel();
        labelSensorFile = new javax.swing.JLabel();
        labelWalkingSpeed = new javax.swing.JLabel();
        labelDays = new javax.swing.JLabel();
        agentsListPanel = new javax.swing.JPanel();
        scrollPanelAgent = new javax.swing.JScrollPane();
        listViewAgent = new javax.swing.JList<>();
        newHomeLabel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfWalkingSpeed = new javax.swing.JTextField();
        tfDays = new javax.swing.JTextField();
        labelActivitiesFile = new javax.swing.JLabel();
        tfHomeSchemeFile = new javax.swing.JTextField();
        tfSensorFile = new javax.swing.JTextField();
        tfActivitiesFile = new javax.swing.JTextField();
        btnHomeBrowse = new javax.swing.JButton();
        btnSensorBrowse = new javax.swing.JButton();
        btnActivitiesBrowse = new javax.swing.JButton();
        labelScenarioName = new javax.swing.JLabel();
        tfScenarioName = new javax.swing.JTextField();
        rightPanel = new javax.swing.JPanel();
        panelAgentTitle = new javax.swing.JPanel();
        labelNewAgent = new javax.swing.JLabel();
        avatarPanel = new javax.swing.JPanel();
        labelAvatar4 = new javax.swing.JLabel();
        labelAvatar1 = new javax.swing.JLabel();
        labelAvatar2 = new javax.swing.JLabel();
        labelAvatar3 = new javax.swing.JLabel();
        rBtnAv1 = new javax.swing.JRadioButton();
        rBtnAv2 = new javax.swing.JRadioButton();
        rBtnAv3 = new javax.swing.JRadioButton();
        rBtnAv4 = new javax.swing.JRadioButton();
        labelAgentName = new javax.swing.JLabel();
        tfAgentName = new javax.swing.JTextField();
        labelStartingPoint = new javax.swing.JLabel();
        tfStartingPoint = new javax.swing.JTextField();
        panelNeeds = new javax.swing.JPanel();
        sliderEnergy = new javax.swing.JSlider();
        sliderHunger = new javax.swing.JSlider();
        sliderBladder = new javax.swing.JSlider();
        sliderHygiene = new javax.swing.JSlider();
        sliderFun = new javax.swing.JSlider();
        labelEnergy = new javax.swing.JLabel();
        labelHunger = new javax.swing.JLabel();
        labelBladder = new javax.swing.JLabel();
        labelHygiene = new javax.swing.JLabel();
        labelFun = new javax.swing.JLabel();
        valueEnergy = new javax.swing.JLabel();
        valueBladder = new javax.swing.JLabel();
        valueHygiene = new javax.swing.JLabel();
        valueFun = new javax.swing.JLabel();
        valueHunger = new javax.swing.JLabel();
        btnAddAgent = new javax.swing.JButton();
        btnSaveScenario = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(350);

        labelHomeSchemeFile.setText("Home Scheme File:");

        labelSensorFile.setText("Sensor File: ");

        labelWalkingSpeed.setText("Walking Speed:");

        labelDays.setText("Days: ");

        agentsListPanel.setBackground(new java.awt.Color(250, 250, 250));
        agentsListPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agents List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 188, 212))); // NOI18N

        listViewAgent.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollPanelAgent.setViewportView(listViewAgent);

        javax.swing.GroupLayout agentsListPanelLayout = new javax.swing.GroupLayout(agentsListPanel);
        agentsListPanel.setLayout(agentsListPanelLayout);
        agentsListPanelLayout.setHorizontalGroup(
            agentsListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agentsListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPanelAgent)
                .addContainerGap())
        );
        agentsListPanelLayout.setVerticalGroup(
            agentsListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agentsListPanelLayout.createSequentialGroup()
                .addComponent(scrollPanelAgent, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        newHomeLabel.setBackground(new java.awt.Color(0, 188, 212));
        newHomeLabel.setMaximumSize(new java.awt.Dimension(99, 22));
        newHomeLabel.setMinimumSize(new java.awt.Dimension(99, 22));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("New Scenario");

        javax.swing.GroupLayout newHomeLabelLayout = new javax.swing.GroupLayout(newHomeLabel);
        newHomeLabel.setLayout(newHomeLabelLayout);
        newHomeLabelLayout.setHorizontalGroup(
            newHomeLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newHomeLabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        newHomeLabelLayout.setVerticalGroup(
            newHomeLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        labelActivitiesFile.setText("Activities File :");

        tfHomeSchemeFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHomeSchemeFileActionPerformed(evt);
            }
        });

        btnHomeBrowse.setText("Browse");
        btnHomeBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeBrowseActionPerformed(evt);
            }
        });

        btnSensorBrowse.setText("Browse");
        btnSensorBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSensorBrowseActionPerformed(evt);
            }
        });

        btnActivitiesBrowse.setText("Browse");
        btnActivitiesBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivitiesBrowseActionPerformed(evt);
            }
        });

        labelScenarioName.setText("Scenario Name :");

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(newHomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agentsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelScenarioName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelDays, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelWalkingSpeed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelActivitiesFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelSensorFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelHomeSchemeFile, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addComponent(tfHomeSchemeFile, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHomeBrowse))
                            .addComponent(tfScenarioName, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfWalkingSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDays, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(leftPanelLayout.createSequentialGroup()
                                        .addComponent(tfActivitiesFile, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(leftPanelLayout.createSequentialGroup()
                                        .addComponent(tfSensorFile)
                                        .addGap(4, 4, 4)))
                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSensorBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnActivitiesBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(newHomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelScenarioName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfScenarioName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addComponent(labelHomeSchemeFile, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSensorFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSensorFile, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSensorBrowse))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelActivitiesFile, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfActivitiesFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActivitiesBrowse))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelWalkingSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfWalkingSpeed))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDays, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDays))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(agentsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfHomeSchemeFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHomeBrowse))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(leftPanel);

        panelAgentTitle.setBackground(new java.awt.Color(0, 188, 212));

        labelNewAgent.setBackground(new java.awt.Color(0, 188, 212));
        labelNewAgent.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelNewAgent.setForeground(new java.awt.Color(255, 255, 255));
        labelNewAgent.setText("New Agent");

        javax.swing.GroupLayout panelAgentTitleLayout = new javax.swing.GroupLayout(panelAgentTitle);
        panelAgentTitle.setLayout(panelAgentTitleLayout);
        panelAgentTitleLayout.setHorizontalGroup(
            panelAgentTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgentTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNewAgent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAgentTitleLayout.setVerticalGroup(
            panelAgentTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelNewAgent, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        avatarPanel.setBackground(new java.awt.Color(250, 250, 250));

        labelAvatar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/running.gif"))); // NOI18N
        labelAvatar4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/boy_avatar_48p.png"))); // NOI18N
        labelAvatar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelAvatar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/girl_avatar_48p.png"))); // NOI18N
        labelAvatar2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelAvatar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/grandpa.gif"))); // NOI18N
        labelAvatar3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        rBtnAv1.setBackground(new java.awt.Color(250, 250, 250));
        btnGroupAvatar.add(rBtnAv1);
        rBtnAv1.setText("Avatar 1");

        rBtnAv2.setBackground(new java.awt.Color(250, 250, 250));
        btnGroupAvatar.add(rBtnAv2);
        rBtnAv2.setText("Avatar 2");

        rBtnAv3.setBackground(new java.awt.Color(250, 250, 250));
        btnGroupAvatar.add(rBtnAv3);
        rBtnAv3.setText("Avatar 3");

        rBtnAv4.setBackground(new java.awt.Color(250, 250, 250));
        btnGroupAvatar.add(rBtnAv4);
        rBtnAv4.setText("Avatar 4");

        javax.swing.GroupLayout avatarPanelLayout = new javax.swing.GroupLayout(avatarPanel);
        avatarPanel.setLayout(avatarPanelLayout);
        avatarPanelLayout.setHorizontalGroup(
            avatarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avatarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(avatarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rBtnAv1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(labelAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(avatarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(avatarPanelLayout.createSequentialGroup()
                        .addComponent(rBtnAv2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rBtnAv3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rBtnAv4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(avatarPanelLayout.createSequentialGroup()
                        .addComponent(labelAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelAvatar3)
                        .addGap(18, 18, 18)
                        .addComponent(labelAvatar4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        avatarPanelLayout.setVerticalGroup(
            avatarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avatarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(avatarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAvatar4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(labelAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAvatar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAvatar3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(avatarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rBtnAv1)
                    .addComponent(rBtnAv2)
                    .addComponent(rBtnAv3)
                    .addComponent(rBtnAv4))
                .addContainerGap())
        );

        labelAgentName.setText("Agent Name:");

        labelStartingPoint.setText("Starting Point (default 1L): ");

        panelNeeds.setBackground(new java.awt.Color(250, 250, 250));
        panelNeeds.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agent's Needs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 188, 212))); // NOI18N

        sliderEnergy.setBackground(new java.awt.Color(250, 250, 250));
        sliderEnergy.setMajorTickSpacing(2);
        sliderEnergy.setMaximum(20);
        sliderEnergy.setMinimum(10);
        sliderEnergy.setMinorTickSpacing(1);
        sliderEnergy.setPaintLabels(true);
        sliderEnergy.setPaintTicks(true);

        sliderHunger.setBackground(new java.awt.Color(250, 250, 250));
        sliderHunger.setMajorTickSpacing(2);
        sliderHunger.setMaximum(20);
        sliderHunger.setMinimum(10);
        sliderHunger.setMinorTickSpacing(1);
        sliderHunger.setPaintLabels(true);
        sliderHunger.setPaintTicks(true);

        sliderBladder.setBackground(new java.awt.Color(250, 250, 250));
        sliderBladder.setMajorTickSpacing(2);
        sliderBladder.setMaximum(20);
        sliderBladder.setMinimum(10);
        sliderBladder.setMinorTickSpacing(1);
        sliderBladder.setPaintLabels(true);
        sliderBladder.setPaintTicks(true);

        sliderHygiene.setBackground(new java.awt.Color(250, 250, 250));
        sliderHygiene.setMajorTickSpacing(2);
        sliderHygiene.setMaximum(20);
        sliderHygiene.setMinimum(10);
        sliderHygiene.setMinorTickSpacing(1);
        sliderHygiene.setPaintLabels(true);
        sliderHygiene.setPaintTicks(true);

        sliderFun.setBackground(new java.awt.Color(250, 250, 250));
        sliderFun.setMajorTickSpacing(2);
        sliderFun.setMaximum(20);
        sliderFun.setMinimum(10);
        sliderFun.setMinorTickSpacing(1);
        sliderFun.setPaintLabels(true);
        sliderFun.setPaintTicks(true);

        labelEnergy.setText("Energy Decay Rate :");

        labelHunger.setText("Hunger Decay Rate :");

        labelBladder.setText("Bladder Decay Rate :");

        labelHygiene.setText("Hygiene Decay Rate :");

        labelFun.setText("Fun Decay Rate :");

        valueEnergy.setText("1");

        valueBladder.setText("1");

        valueHygiene.setText("1");

        valueFun.setText("1");

        valueHunger.setText("1");

        javax.swing.GroupLayout panelNeedsLayout = new javax.swing.GroupLayout(panelNeeds);
        panelNeeds.setLayout(panelNeedsLayout);
        panelNeedsLayout.setHorizontalGroup(
            panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNeedsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelHunger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelBladder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelHygiene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelNeedsLayout.createSequentialGroup()
                            .addComponent(sliderEnergy, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valueEnergy, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNeedsLayout.createSequentialGroup()
                            .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(sliderHygiene, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(sliderFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valueFun, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)))
                    .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(valueHygiene, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelNeedsLayout.createSequentialGroup()
                            .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(sliderHunger, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(sliderBladder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(valueHunger, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                .addComponent(valueBladder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        panelNeedsLayout.setVerticalGroup(
            panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNeedsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelEnergy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valueEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sliderHunger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHunger, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valueHunger, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelBladder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sliderBladder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valueBladder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelHygiene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sliderHygiene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valueHygiene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNeedsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sliderFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valueFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnAddAgent.setText("Add Agent");
        btnAddAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAgentActionPerformed(evt);
            }
        });

        btnSaveScenario.setText("Save Scenario");
        btnSaveScenario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveScenarioActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAgentTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveScenario, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelNeeds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(avatarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAgentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelStartingPoint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfStartingPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                            .addComponent(tfAgentName))))
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addComponent(panelAgentTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(avatarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAgentName)
                    .addComponent(tfAgentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStartingPoint)
                    .addComponent(tfStartingPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNeeds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveScenario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddAgent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

    private void btnHomeBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeBrowseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeBrowseActionPerformed

    private void btnSensorBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSensorBrowseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSensorBrowseActionPerformed

    private void btnActivitiesBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivitiesBrowseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActivitiesBrowseActionPerformed

    private void btnAddAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAgentActionPerformed
        String agentName = tfAgentName.getText();
        String avatarImg = getSelectedAvatarPath();
        double energyValue = Double.valueOf(valueEnergy.getText());
        double hungerValue = Double.valueOf(valueHunger.getText());
        double bladderValue = Double.valueOf(valueBladder.getText());
        double hygieneValue = Double.valueOf(valueHygiene.getText());
        double funValue = Double.valueOf(valueFun.getText());
        Long startingNodeId = Long.parseLong(tfStartingPoint.getText());
        Point currentLocation = nodeRepo.getNodeById(startingNodeId).getLocation();
        List<Need> needs = AgentUtil.getNeeds(energyValue, hungerValue, bladderValue, hygieneValue, funValue);

        Agent a = new Agent(agentName, avatarImg, currentLocation, needs);
        boolean result = agentRepo.saveAgentsToScenario(a, scenarioToBeSaved.getId());

        if (result) {
            agentsDisplayList.add(a);
            agentsListModel.addElement(a.getName());
            listViewAgent.setModel(agentsListModel);
            tfAgentName.setText("");
            tfStartingPoint.setText("");
            scenarioRepo.addAgentToScenario(agentRepo.getLastAgentInsertedId(), scenarioRepo.getLastInsertedScenarioId());
            JOptionPane.showMessageDialog(null, "Agent added to scenario!");
        } else {
            JOptionPane.showMessageDialog(null, "Agent didn't saved succesfully");
        }

    }//GEN-LAST:event_btnAddAgentActionPerformed

    private void btnSaveScenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveScenarioActionPerformed
        scenarioToBeSaved = new Scenario();
        scenarioToBeSaved.setHouseFile(tfHomeSchemeFile.getText());
        scenarioToBeSaved.setTaskile(tfActivitiesFile.getText());
        scenarioToBeSaved.setSensorFile(tfActivitiesFile.getText());
        scenarioToBeSaved.setDays(Integer.parseInt(tfDays.getText()));
        scenarioToBeSaved.setName(tfScenarioName.getText());
        scenarioToBeSaved.setStartingPoint(Long.parseLong(tfStartingPoint.getText()));
        scenarioRepo.saveScenario(scenarioToBeSaved);
        scenarioToBeSaved.setId(scenarioRepo.getLastInsertedScenarioId());
        btnSaveScenario.setEnabled(false);
    }//GEN-LAST:event_btnSaveScenarioActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tfHomeSchemeFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHomeSchemeFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHomeSchemeFileActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel agentsListPanel;
    private javax.swing.JPanel avatarPanel;
    private javax.swing.JButton btnActivitiesBrowse;
    private javax.swing.JButton btnAddAgent;
    private javax.swing.JButton btnCancel;
    private javax.swing.ButtonGroup btnGroupAvatar;
    private javax.swing.JButton btnHomeBrowse;
    private javax.swing.JButton btnSaveScenario;
    private javax.swing.JButton btnSensorBrowse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel labelActivitiesFile;
    private javax.swing.JLabel labelAgentName;
    private javax.swing.JLabel labelAvatar1;
    private javax.swing.JLabel labelAvatar2;
    private javax.swing.JLabel labelAvatar3;
    private javax.swing.JLabel labelAvatar4;
    private javax.swing.JLabel labelBladder;
    private javax.swing.JLabel labelDays;
    private javax.swing.JLabel labelEnergy;
    private javax.swing.JLabel labelFun;
    private javax.swing.JLabel labelHomeSchemeFile;
    private javax.swing.JLabel labelHunger;
    private javax.swing.JLabel labelHygiene;
    private javax.swing.JLabel labelNewAgent;
    private javax.swing.JLabel labelScenarioName;
    private javax.swing.JLabel labelSensorFile;
    private javax.swing.JLabel labelStartingPoint;
    private javax.swing.JLabel labelWalkingSpeed;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JList<String> listViewAgent;
    private javax.swing.JPanel newHomeLabel;
    private javax.swing.JPanel panelAgentTitle;
    private javax.swing.JPanel panelNeeds;
    private javax.swing.JRadioButton rBtnAv1;
    private javax.swing.JRadioButton rBtnAv2;
    private javax.swing.JRadioButton rBtnAv3;
    private javax.swing.JRadioButton rBtnAv4;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JScrollPane scrollPanelAgent;
    private javax.swing.JSlider sliderBladder;
    private javax.swing.JSlider sliderEnergy;
    private javax.swing.JSlider sliderFun;
    private javax.swing.JSlider sliderHunger;
    private javax.swing.JSlider sliderHygiene;
    private javax.swing.JTextField tfActivitiesFile;
    private javax.swing.JTextField tfAgentName;
    private javax.swing.JTextField tfDays;
    private javax.swing.JTextField tfHomeSchemeFile;
    private javax.swing.JTextField tfScenarioName;
    private javax.swing.JTextField tfSensorFile;
    private javax.swing.JTextField tfStartingPoint;
    private javax.swing.JTextField tfWalkingSpeed;
    private javax.swing.JLabel valueBladder;
    private javax.swing.JLabel valueEnergy;
    private javax.swing.JLabel valueFun;
    private javax.swing.JLabel valueHunger;
    private javax.swing.JLabel valueHygiene;
    // End of variables declaration//GEN-END:variables

    private String getSelectedAvatarPath() {
        String path = null;
        if (rBtnAv1.isSelected()) {
            path = "/boy_avatar_48p.png";
        } else if (rBtnAv2.isSelected()) {
            path = "/girl_avatar_48p.png";
        } else if (rBtnAv3.isSelected()) {
            path = "/grandpa.gif";
        } else if (rBtnAv4.isSelected()) {
            path = "/running.gif";
        }
        return path;
    }

    private void disableFields() {
        tfActivitiesFile.setText("/activities.json");
        tfSensorFile.setText("/sensors.json");
        tfHomeSchemeFile.setText("/environment.jpg");
        tfDays.setText(30 + "");
        tfWalkingSpeed.setText(30 + "");
        tfActivitiesFile.setEnabled(false);
        tfSensorFile.setEnabled(false);
        tfHomeSchemeFile.setEnabled(false);
        tfDays.setEnabled(false);
        tfWalkingSpeed.setEnabled(false);
    }
}
