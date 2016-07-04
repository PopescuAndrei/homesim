/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.model.Scenario;
import ro.fils.smarthome.repository.AgentRepository;
import ro.fils.smarthome.repository.ScenarioRepository;
import ro.fils.smarthome.parsers.LogReader;

/**
 *
 * @author andre
 */
public class AnalyticsFrame extends javax.swing.JFrame {

    private List<Scenario> scenarios;
    private List<Agent> agents;

    private List<String> analyticsNames;
    private List<String> scenarioNames;
    private List<String> agentNames;
    private List<String> logFileNames;

    private DefaultListModel<String> analyticsModel;
    private final DefaultComboBoxModel<String> scenarioComboModel;
    private DefaultComboBoxModel<String> agentComboModel;
    private DefaultComboBoxModel<String> logComboModel;
    private DefaultComboBoxModel<String> dayComboModel;

    private ScenarioRepository scenarioRepo;
    private AgentRepository agentRepo;
    private final String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public AnalyticsFrame() {
        initComponents();
        this.setTitle("Analytics");

        scenarioRepo = new ScenarioRepository();
        agentRepo = new AgentRepository();

        analyticsNames = buildList();
        analyticsModel = new DefaultListModel<>();
        scenarioComboModel = new DefaultComboBoxModel<>();
        agentComboModel = new DefaultComboBoxModel<>();
        logComboModel = new DefaultComboBoxModel<>();
        dayComboModel = new DefaultComboBoxModel<>();

        scenarios = scenarioRepo.getAllScenarios();
        for (Scenario s : scenarios) {
            scenarioComboModel.addElement(s.getName());
        }
        comboScenarios.setModel(scenarioComboModel);

        for (String name : analyticsNames) {
            analyticsModel.addElement(name);
        }
        listViewAnalytics.setModel(analyticsModel);

        
        for (String weekDay : weekDays) {
            dayComboModel.addElement(weekDay);
        }
        comboDay.setModel(dayComboModel);

        listViewAnalytics.addListSelectionListener((ListSelectionEvent e) -> {
            labelTitle.setText(analyticsNames.get(listViewAnalytics.getSelectedIndex()));
            disableAllCombos();
            switch (listViewAnalytics.getSelectedIndex()) {
                case 0:
                    comboAgents.setEnabled(true);
                    comboFiles.setEnabled(true);
                    comboScenarios.setEnabled(true);
                    comboDay.setEnabled(false);
                    break;
                case 1:
                    comboAgents.setEnabled(true);
                    comboFiles.setEnabled(true);
                    comboScenarios.setEnabled(true);
                    comboDay.setEnabled(false);
                    break;
                case 2:
                    comboAgents.setEnabled(true);
                    comboFiles.setEnabled(true);
                    comboScenarios.setEnabled(true);
                    comboDay.setEnabled(true);
                    break;
                default:
                    break;
            }
        });

        comboScenarios.addItemListener((ItemEvent e) -> {
            agentComboModel.removeAllElements();
            logComboModel.removeAllElements();

            agents = agentRepo.getAgentsForScenario(scenarios.get(comboScenarios.getSelectedIndex()).getId());
            for (Agent ag : agents) {
                agentComboModel.addElement(ag.getName());
            }
            comboAgents.setModel(agentComboModel);

            logComboModel.removeAllElements();
            logFileNames = LogReader.getAllLogsFromFolderForScenario(scenarios.get(comboScenarios.getSelectedIndex()).getName());
            for (String logFile : logFileNames) {
                logComboModel.addElement(logFile);
            }
            comboFiles.setModel(logComboModel);
        });
        disableAllCombos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainSplitPanel = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listViewAnalytics = new javax.swing.JList<>();
        rightPanel = new javax.swing.JPanel();
        panelTitle = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        panelCombos = new javax.swing.JPanel();
        labelScenario = new javax.swing.JLabel();
        labelAgent = new javax.swing.JLabel();
        labelLogs = new javax.swing.JLabel();
        comboScenarios = new javax.swing.JComboBox<>();
        comboAgents = new javax.swing.JComboBox<>();
        comboFiles = new javax.swing.JComboBox<>();
        btnApplyFilters = new javax.swing.JButton();
        labelDay = new javax.swing.JLabel();
        comboDay = new javax.swing.JComboBox<>();
        chartContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainSplitPanel.setDividerLocation(300);

        leftPanel.setBackground(new java.awt.Color(250, 250, 250));

        listViewAnalytics.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analytics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 188, 212))); // NOI18N
        listViewAnalytics.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listViewAnalytics);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainSplitPanel.setLeftComponent(leftPanel);

        rightPanel.setBackground(new java.awt.Color(250, 250, 250));

        panelTitle.setBackground(new java.awt.Color(0, 188, 212));

        labelTitle.setBackground(new java.awt.Color(255, 255, 255));
        labelTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(255, 255, 255));
        labelTitle.setText("Title");

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
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        panelCombos.setBackground(new java.awt.Color(200, 200, 200));

        labelScenario.setText("Scenario :");

        labelAgent.setText("Agent :");

        labelLogs.setText("Log File :");

        comboScenarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboAgents.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboFiles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnApplyFilters.setText("Apply Filters");
        btnApplyFilters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyFiltersActionPerformed(evt);
            }
        });

        labelDay.setText("Day :");

        comboDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelCombosLayout = new javax.swing.GroupLayout(panelCombos);
        panelCombos.setLayout(panelCombosLayout);
        panelCombosLayout.setHorizontalGroup(
            panelCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCombosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnApplyFilters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCombosLayout.createSequentialGroup()
                        .addGroup(panelCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelDay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelScenario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelAgent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(labelLogs)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboFiles, 0, 379, Short.MAX_VALUE)
                            .addComponent(comboScenarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboAgents, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboDay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelCombosLayout.setVerticalGroup(
            panelCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCombosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelScenario)
                    .addComponent(comboScenarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAgent)
                    .addComponent(comboAgents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLogs)
                    .addComponent(comboFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDay)
                    .addComponent(comboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnApplyFilters, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        chartContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 188, 212))); // NOI18N
        chartContainer.setPreferredSize(new java.awt.Dimension(120, 240));
        chartContainer.setLayout(new javax.swing.BoxLayout(chartContainer, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chartContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCombos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCombos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chartContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
        );

        mainSplitPanel.setRightComponent(rightPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPanel)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnApplyFiltersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyFiltersActionPerformed
        if (listViewAnalytics.getSelectedIndex() == 0) {
            buildMetersChart();
        } else if (listViewAnalytics.getSelectedIndex() == 1) {
            buildCoverageChart();
        } else {
            buildMovementDetectChart();
        }
    }//GEN-LAST:event_btnApplyFiltersActionPerformed

    ChartPanel initChartPanel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApplyFilters;
    private javax.swing.JPanel chartContainer;
    private javax.swing.JComboBox<String> comboAgents;
    private javax.swing.JComboBox<String> comboDay;
    private javax.swing.JComboBox<String> comboFiles;
    private javax.swing.JComboBox<String> comboScenarios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAgent;
    private javax.swing.JLabel labelDay;
    private javax.swing.JLabel labelLogs;
    private javax.swing.JLabel labelScenario;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JList<String> listViewAnalytics;
    private javax.swing.JSplitPane mainSplitPanel;
    private javax.swing.JPanel panelCombos;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JPanel rightPanel;
    // End of variables declaration//GEN-END:variables

    private List<String> buildList() {
        List<String> list = new ArrayList<>();
        list.add("Traveled Meters by Day");
        list.add("Sensor Coverage");
        list.add("No Movement Detection");
        return list;
    }

    private void disableAllCombos() {
        comboAgents.setEnabled(false);
        comboFiles.setEnabled(false);
        comboScenarios.setEnabled(false);
        comboDay.setEnabled(false);
    }

    private void buildMetersChart() {
        String selectedLog = logFileNames.get(comboFiles.getSelectedIndex());
        int selectedScenario = scenarios.get(comboScenarios.getSelectedIndex()).getId();
        String selectedAgent = agents.get(comboAgents.getSelectedIndex()).getName();
        Map<String, Double> map = LogReader.getTraveledMetersForLogFile(selectedLog, selectedScenario, selectedAgent);
        final String MONDAY = "Monday";
        final String TUESDAY = "Tuesday";
        final String WEDNESDAY = "Wednesday";
        final String THURSDAY = "Thursday";
        final String FRIDAY = "Friday";
        final String SATURDAY = "Saturday";
        final String SUNDAY = "Sunday";

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.addValue(map.get(MONDAY), "Week Day", MONDAY);
        dataSet.addValue(map.get(TUESDAY), "Week Day", TUESDAY);
        dataSet.addValue(map.get(WEDNESDAY), "Week Day", WEDNESDAY);
        dataSet.addValue(map.get(THURSDAY), "Week Day", THURSDAY);
        dataSet.addValue(map.get(FRIDAY), "Week Day", FRIDAY);
        dataSet.addValue(map.get(SATURDAY), "Week Day", SATURDAY);
        dataSet.addValue(map.get(SUNDAY), "Week Day", SUNDAY);

        JFreeChart traveledMetersChart = ChartFactory.createBarChart("TraveledKms",
                "Week Day",
                "Distance [m]",
                dataSet,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        CategoryPlot plot = (CategoryPlot) traveledMetersChart.getPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        ChartPanel barChartPanel = new ChartPanel(traveledMetersChart);
        chartContainer.removeAll();
        chartContainer.add(barChartPanel);
        chartContainer.updateUI();
    }

    private void buildCoverageChart() {
        String selectedLog = logFileNames.get(comboFiles.getSelectedIndex());
        String referenceLog = LogReader.getReferenceLogPathForScenario(scenarios.get(comboScenarios.getSelectedIndex()).getName());
        int selectedScenario = scenarios.get(comboScenarios.getSelectedIndex()).getId();
        String selectedAgent = agents.get(comboAgents.getSelectedIndex()).getName();
        double numberOfReadings = LogReader.getSensorReadings(selectedLog, selectedScenario, selectedAgent);
        System.out.println(numberOfReadings);
        double numberOfReadingsReference = LogReader.getSensorReadings(referenceLog, selectedScenario, selectedAgent);
        System.out.println(numberOfReadingsReference);

        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {2}");
        DefaultPieDataset dataSet = new DefaultPieDataset();
        dataSet.setValue("Number of Readings (Selected Simulation)", numberOfReadings);
        dataSet.setValue("Number of Readings (Reference Simulation)", numberOfReadingsReference);

        JFreeChart pieChart = ChartFactory.createPieChart("Sensor Coverage",
                dataSet,
                true,
                true,
                false);
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setExplodePercent("3", 0.25);
        plot.setLabelGenerator(labelGenerator);
        plot.setCircular(true);
        chartContainer.removeAll();
        chartContainer.add(pieChartPanel);
        chartContainer.updateUI();
    }

    private void buildMovementDetectChart() {
        String selectedLog = logFileNames.get(comboFiles.getSelectedIndex());
        String selectedAgent = agents.get(comboAgents.getSelectedIndex()).getName();
        String choosenDay = weekDays[comboDay.getSelectedIndex()];
        Map<String, Integer> map = LogReader.getMovementStatistics(selectedLog, selectedAgent, choosenDay);

        DefaultPieDataset dataSet = new DefaultPieDataset();

        for (String key : map.keySet()) {
            if (!key.contains("Door")){
                dataSet.setValue(key, map.get(key));
            }
        }

        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1}s ({2})");
        JFreeChart pieChart = ChartFactory.createPieChart("No Movement Detection",
                dataSet,
                true,
                true,
                false);
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setExplodePercent("3", 0.25);
        plot.setLabelGenerator(labelGenerator);
        plot.setCircular(true);
        chartContainer.removeAll();
        chartContainer.add(pieChartPanel);
        chartContainer.updateUI();
    }
}
