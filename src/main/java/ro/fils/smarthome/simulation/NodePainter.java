/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.simulation;

import java.awt.Dimension;
import java.util.ArrayList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javax.swing.ImageIcon;
import ro.fils.smarthome.model.Edge;
import ro.fils.smarthome.model.Node;

/**
 *
 * @author andre
 */
public class NodePainter extends JFXPanel {

    int xPressed, yPressed;
    int xReleased, yReleased;
    int xDragged, yDragged;

    Node selectedPoint;
    Node hoveredPoint;
    DesignFrame simFrame;

    private Image image;
    private ArrayList<Edge> edges;
    private ArrayList<Node> points;

    public NodePainter(DesignFrame frame, String imageFile) {
        this.simFrame = frame;
        image = new Image(imageFile);
        //setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }
}
