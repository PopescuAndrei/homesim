/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.fils.smarthome.model.Appliance;
import ro.fils.smarthome.planManagement.Node;
import ro.fils.smarthome.service.NodeObjectService;
import ro.fils.smarthome.service.NodeService;
import ro.fils.smarthome.simulation.NodePainter;
import ro.fils.smarthome.tasksManagement.TaskReader;

/**
 *
 * @author andre
 */
public class DesignFrame extends javax.swing.JFrame implements ActionListener {

    ClassPathXmlApplicationContext ctx;
    private NodePainter painter;
    private Node selectedNode;
    private DefaultComboBoxModel comboBoxModel;
    private DefaultListModel<String> listModel;
    NodeObjectService nodeObjectService;
    NodeService nodeService;

    public DesignFrame(ClassPathXmlApplicationContext ctx) {
        this.ctx = ctx;
        nodeObjectService = ctx.getBean(NodeObjectService.class);
        nodeService = ctx.getBean(NodeService.class);
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        labelAddNode = new javax.swing.JLabel();
        labelSelectNode = new javax.swing.JLabel();
        labelLinkNodes = new javax.swing.JLabel();
        labelMoveNode = new javax.swing.JLabel();
        labelRestartPart1 = new javax.swing.JLabel();
        labelRestartPart2 = new javax.swing.JLabel();
        actionsPanel = new javax.swing.JPanel();
        isStart = new javax.swing.JCheckBox();
        chooser = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lister = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        btnAddAppliance = new javax.swing.JButton();
        btnRemoveAppliance = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        editPoses = new javax.swing.JTextField();
        btnUpdatePoses = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setResizeWeight(0.3);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(leftPanel);
        try{
            painter = new NodePainter(ctx, this, "environment.png");
        }catch(Exception e){
            e.printStackTrace();
        }
        leftPanel.add(painter);

        labelAddNode.setText("Right-click: Add node");

        labelSelectNode.setText("Left-click: Select node");

        labelLinkNodes.setText("CTRL+Left-click: Link nodes");

        labelMoveNode.setText("Shift+drag: Move node");

        labelRestartPart1.setText("Restart simulator for changes");

        labelRestartPart2.setText("to take effect!");

        actionsPanel.setBackground(new java.awt.Color(200, 200, 200));
        actionsPanel.setMinimumSize(new java.awt.Dimension(300, 313));

        isStart.setBackground(new java.awt.Color(200, 200, 200));
        isStart.setText("Starting Point");

        chooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lister.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lister);

        jLabel2.setText("Node's Appliances:");

        btnAddAppliance.setText("Add Appliance to Node");

        btnRemoveAppliance.setText("Remove Appliance from Node");

        jLabel3.setText("Available Poses :");

        btnUpdatePoses.setText("Update Poses");

        javax.swing.GroupLayout actionsPanelLayout = new javax.swing.GroupLayout(actionsPanel);
        actionsPanel.setLayout(actionsPanelLayout);
        actionsPanelLayout.setHorizontalGroup(
            actionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(isStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(actionsPanelLayout.createSequentialGroup()
                        .addGroup(actionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(actionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRemoveAppliance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddAppliance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editPoses)
                            .addComponent(btnUpdatePoses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        actionsPanelLayout.setVerticalGroup(
            actionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(isStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(actionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(actionsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(actionsPanelLayout.createSequentialGroup()
                        .addComponent(btnAddAppliance)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemoveAppliance)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editPoses, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdatePoses, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Node Actions");

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actionsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSelectNode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLinkNodes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMoveNode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelRestartPart1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                    .addComponent(labelRestartPart2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAddNode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAddNode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSelectNode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLinkNodes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMoveNode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(labelRestartPart1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelRestartPart2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        jSplitPane1.setRightComponent(rightPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setActiveNode(Node selectedPoint) throws Exception {
        isStart.addActionListener(this);
        int start = 0;
        System.out.println(selectedPoint.toString());
        if (selectedPoint.getId() == start) {
            System.out.println("Ci plm are?");
            isStart.setSelected(true);
            isStart.setEnabled(false);
        }

        selectedNode = selectedPoint;
        TaskReader taskReader = new TaskReader();
        Set<String> objectList = taskReader.getAppliances();
        comboBoxModel = new DefaultComboBoxModel();
        objectList.forEach(o -> comboBoxModel.addElement(o));
        chooser.setModel(comboBoxModel);

        btnAddAppliance.addActionListener((ActionEvent ae) -> {
            selectedNode.addAppliance(chooser.getSelectedItem().toString());
            nodeService.updateNode(selectedNode);
            try {
                setActiveNode(selectedNode);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "You must first select a node");
            }
        });

        btnRemoveAppliance.addActionListener((ActionEvent ae) -> {
            selectedNode.removeAppliance(lister.getSelectedValue());
            try {
                setActiveNode(selectedNode);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "You must first select a node");
            }
        });

        Iterator it = selectedPoint.getApplianceTypes().iterator();
        DefaultListModel<String> nodeObjects = new DefaultListModel<>();
        while (it.hasNext()) {
            Appliance obj = (Appliance) it.next();
            nodeObjects.addElement(obj.getType());
        }

        lister.setModel(nodeObjects);
        lister.setVisibleRowCount(5);

        lister.addListSelectionListener((ListSelectionEvent lse) -> {
            if (!lse.getValueIsAdjusting()) {
                String selected = lister.getSelectedValue();

                selectedNode.getApplianceTypes().stream().filter((app) -> (app.getType().equals(selected))).forEach((app) -> {
                    StringBuilder sb = new StringBuilder();
                    app.getPoses().stream().forEach((pose) -> {
                        sb.append(pose).append(" ");
                    });
                    editPoses.setText(sb.toString());

                    btnUpdatePoses.addActionListener((ActionEvent ae) -> {
                        nodeObjectService.updateNodeObjectPosesByIdAndType(editPoses.getText(), selectedNode.getId(), app.getType());

                    });
                });
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JButton btnAddAppliance;
    private javax.swing.JButton btnRemoveAppliance;
    private javax.swing.JButton btnUpdatePoses;
    private javax.swing.JComboBox<String> chooser;
    private javax.swing.JTextField editPoses;
    private javax.swing.JCheckBox isStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel labelAddNode;
    private javax.swing.JLabel labelLinkNodes;
    private javax.swing.JLabel labelMoveNode;
    private javax.swing.JLabel labelRestartPart1;
    private javax.swing.JLabel labelRestartPart2;
    private javax.swing.JLabel labelSelectNode;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JList<String> lister;
    private javax.swing.JPanel rightPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
