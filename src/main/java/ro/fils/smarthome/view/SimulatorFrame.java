/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private HashMap<String, JProgressBar> needBars = new HashMap<>();
    private JLabel stateList;
    private JLabel timeLabel;

    /**
     * Creates new form SimulatorFrame
     */
    public SimulatorFrame(ClassPathXmlApplicationContext ctx) {
        this.context = ctx;
        initSimulatorTools();
        initComponents();
        setExtendedState(Frame.MAXIMIZED_BOTH);
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

    private Box populateAgentsAndNeeds(String agentName) {

        Box allNeeds = Box.createVerticalBox();
        Collection<Agent> allAgents = simulationMap.getPeople();
        for (Agent person : allAgents) {
            if (agentName.equals(person.getName())) {
                List<Need> personNeeds = person.getNeeds();
                JPanel needsPanel = new JPanel(new GridLayout(person.getNeeds().size() + 1, 2));

                Font boldFont = new Font(Font.SERIF, Font.BOLD, 20);

                JLabel jLabel = new JLabel("Agent:");
//                jLabel.setFont(boldFont);
                needsPanel.add(jLabel);
                JLabel jLabel2 = new JLabel(person.getName());
//                jLabel2.setFont(boldFont);
                needsPanel.add(jLabel2);

                for (Need need : personNeeds) {
                    needsPanel.add(new JLabel(need.getName()));
                    JProgressBar prog = new JProgressBar(0, 100);
                    prog.setName(person.getName() + "," + need.getName());
                    prog.setValue((int) need.getValue());
                    prog.setString("" + (int) need.getValue());
                    prog.setStringPainted(true);
                    needBars.put(prog.getName(), prog);
                    needsPanel.add(prog);
                    needsPanel.setMaximumSize(needsPanel.getPreferredSize());
                    allNeeds.add(needsPanel);
                }
            }
        }
        return allNeeds;
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

    public void updateMenu() {
        Collection<Agent> people = simulationMap.getPeople();
        String info = "";
        info = people.stream().map((person) -> {
            List<Need> needs = person.getNeeds();
            needs.stream().forEach((need) -> {
                JProgressBar p = needBars.get(person.getName() + "," + need.getName());
                p.setValue((int) need.getValue());
                p.setString("" + (int) need.getValue());
            });
            return person;
        }).map((person) -> person.getName() + person.getState() + "\n").reduce(info, String::concat);
        stateList.setText(info);
        timeLabel.setText("W: " + Time.getWeek(simulator.currentTime)
                + ", D: " + Time.getDayName(simulator.currentTime)
                + ", " + Time.getNumberFormatted(Time.getHours(simulator.currentTime))
                + ":" + Time.getNumberFormatted(Time.getMinutes(simulator.currentTime))
                + ":" + Time.getNumberFormatted(Time.getSeconds(simulator.currentTime)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        left = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setResizeWeight(0.3);

        jLabel1.setText("Logbook:");
        Font font = jLabel1.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        jLabel1.setFont(boldFont);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("List of Agents:");

        jList2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = getAgents();
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList2);

        jButton1.setText("Start");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jSplitPane2.setLeftComponent(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setAlignmentX(0.2F);
        jPanel4.setAlignmentY(0.2F);
        jPanel4.setAutoscrolls(true);

        jLabel2.setText("Duration in Days:");

        jTextField1.setText("30");

        jLabel3.setText("Time - to be displayed");

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(populateAgentsAndNeeds("Person1"));

        jSplitPane2.setRightComponent(jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jSplitPane1.setRightComponent(jPanel1);

        left.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        left.setAlignmentY(0.3F);

        javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
        left.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 152, Short.MAX_VALUE)
        );
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        try {
            display = new SimulationDisplay("/environment.jpg");
            display.update(simulationMap.getPeople(), simulationMap.getSensors());
            left.add(display);

        }catch(Exception e){
            e.printStackTrace();
        }

        jSplitPane1.setLeftComponent(left);

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

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        try {
            simulator.setSensorLogger(new SensorLogger("sensorvals"));
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(SimulatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        days = Integer.parseInt(jTextField1.getText());
        startTime = System.currentTimeMillis();
        timer.start();


    }//GEN-LAST:event_jButton1MouseClicked

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        jPanel2.removeAll();
        jPanel2.repaint();
        String selectedValue = jList2.getSelectedValue();
        jPanel2.add(populateAgentsAndNeeds(selectedValue));
        jPanel2.repaint();
    }//GEN-LAST:event_jList2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SimulatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimulatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimulatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimulatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimulatorFrame(new ClassPathXmlApplicationContext("classpath:applicationContext.xml")).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel left;
    // End of variables declaration//GEN-END:variables
}
