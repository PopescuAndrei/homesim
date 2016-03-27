/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
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
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
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

        javax.swing.GroupLayout actionsPanelLayout = new javax.swing.GroupLayout(actionsPanel);
        actionsPanel.setLayout(actionsPanelLayout);
        actionsPanelLayout.setHorizontalGroup(
            actionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        actionsPanelLayout.setVerticalGroup(
            actionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
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
                    .addComponent(labelRestartPart1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
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
                .addGap(4, 4, 4)
                .addComponent(actionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    //TODO need to check logic of adding boxes one more time
    public void setActiveNode(Node selectedPoint) throws Exception {
        System.out.println("Active: " + selectedPoint.getId());
        actionsPanel.removeAll();
        isStart = new JCheckBox("Starting point");
        isStart.addActionListener(this);
        int start = 0;

        if (selectedPoint.getId() == start) {
            isStart.setSelected(true);
            isStart.setEnabled(false);
        }
        selectedNode = selectedPoint;
        actionsPanel.add(isStart);
        TaskReader taskReader = new TaskReader();
        Set<String> objectList = taskReader.getAppliances();
        chooser = new JComboBox(objectList.toArray());
        actionsPanel.add(chooser);
        Box actionsBox = Box.createHorizontalBox();
        JButton adder = new JButton("Add");
        adder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                selectedNode.addAppliance(chooser.getSelectedItem().toString());
                nodeService.updateNode(selectedNode);
                try {
                    setActiveNode(selectedNode);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                repaint();
            }
        });
        actionsBox.add(adder);
        JButton remover = new JButton("Remove");
        remover.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                selectedNode.removeAppliance(lister.getSelectedValue().toString());
                try {
                    setActiveNode(selectedNode);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                repaint();
            }
        });
        actionsBox.add(remover);
        actionsPanel.add(actionsBox);
        Iterator it = selectedPoint.getApplianceTypes().iterator();
        ArrayList<String> list = new ArrayList<>();
        while (it.hasNext()) {
            Appliance obj = (Appliance) it.next();
            list.add(obj.getType());
        }
        Object[] nodeObjects = new Object[list.size()];
        for (int i = 0; i < nodeObjects.length; i++) {
            nodeObjects[i] = list.get(i);
            System.out.println(list.get(i));
        }
        lister = new JList(nodeObjects);
        lister.setVisibleRowCount(5);
        actionsPanel.add(lister);
        lister.addListSelectionListener((ListSelectionEvent lse) -> {
            if (!lse.getValueIsAdjusting()) {
                String selected = lister.getSelectedValue().toString();
                final JTextField poses = new JTextField(7);
                for (final Appliance app : selectedNode.getApplianceTypes()) {
                    if (app.getType().equals(selected)) {
                        StringBuilder sb = new StringBuilder();
                        app.getPoses().stream().forEach((pose) -> {
                            sb.append(pose).append(" ");
                        });
                        poses.setText(sb.toString());
                        JButton update = new JButton("Update");

                        update.setPreferredSize(new Dimension(70, 30));
                        update.setAction(new AbstractAction("Update") {

                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                nodeObjectService.updateNodeObjectPosesByIdAndType(poses.getText(), selectedNode.getId(), app.getType());
                            }
                        });
                        actionsPanel.removeAll();
                        actionsPanel.add(new JLabel("Poses(space separated)"));
                        actionsPanel.add(poses);
                        actionsPanel.add(update);
                        pack();

                    }
                }
            }
        });
        rightPanel.add(actionsPanel);
        pack();
    }

    private javax.swing.JCheckBox isStart;
    private javax.swing.JComboBox chooser;
    private javax.swing.JList lister;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel rightPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
