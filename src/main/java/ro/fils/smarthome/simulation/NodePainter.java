/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import ro.fils.smarthome.view.DesignFrame;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import ro.fils.smarthome.model.Edge;
import ro.fils.smarthome.model.Node;
import ro.fils.smarthome.repository.EdgeRepository;
import ro.fils.smarthome.repository.NodeRepository;

/**
 *
 * @author andre
 */
public class NodePainter extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    private static final long serialVersionUID = 1L;

    int xPressed, yPressed;
    int xReleased, yReleased;
    int xDragged, yDragged;

    boolean draggingPoint = false;
    boolean drawingLine = false;

    Node selectedPoint;
    Node hoveredPoint;
    DesignFrame simFrame;

    private final ArrayList<Node> points;
    private final Image image;
    ArrayList<Edge> edges;

    private final NodeRepository nodeRepo;
    private final EdgeRepository edgeRepo;

    public NodePainter(DesignFrame frame, String imgFile) {
        this.simFrame = frame;
        nodeRepo = new NodeRepository();
        edgeRepo = new EdgeRepository();

        image = new ImageIcon(getClass().getResource("/environment.jpg").getPath()).getImage();
        initFrame(image);
        points = (ArrayList<Node>) nodeRepo.getNodes();
        edges = (ArrayList<Edge>) edgeRepo.getEdges(points);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);

        g.setColor(new Color(0, 188, 212));
        if (points != null && !points.isEmpty()) {
            for (int i = 0; i < points.size(); i++) {
                g.fillOval(points.get(i).getLocation().x - 4, points.get(i).getLocation().y - 4, 8, 8);
            }
        }

        g.setColor(Color.yellow);
        if (hoveredPoint != null) {
            g.fillOval(hoveredPoint.getLocation().x - 4, hoveredPoint.getLocation().y - 4, 8, 8);
        }

        g.setColor(new Color(0, 188, 212));
        if (selectedPoint != null) {
            g.fillOval(selectedPoint.getLocation().x - 6, selectedPoint.getLocation().y - 6, 12, 12);
        }
        g.setColor(Color.yellow);
        if (selectedPoint != null) {
            g.fillOval(selectedPoint.getLocation().x - 4, selectedPoint.getLocation().y - 4, 8, 8);
        }

        g.setColor(Color.BLACK);

        for (int i = 0; i < edges.size(); i++) {
            g.drawLine(edges.get(i).getA().getLocation().x, edges.get(i).getA().getLocation().y, edges.get(i).getB().getLocation().x, edges.get(i).getB().getLocation().y);
        }
        if (drawingLine) {
            g.drawLine(selectedPoint.getLocation().x, selectedPoint.getLocation().y, xDragged, yDragged);
        }
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        if (draggingPoint && hoveredPoint != null) {
            hoveredPoint.setPosX(arg0.getX());
            hoveredPoint.setPosY(arg0.getY());
        }
        repaint(); //request Swing to refresh display as soon as it can
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        if (hoveredPoint != null) {
            if (arg0.getX() < hoveredPoint.getLocation().x - 4 || arg0.getX() > hoveredPoint.getLocation().x + 4
                    || arg0.getY() < hoveredPoint.getLocation().y - 4 || arg0.getY() > hoveredPoint.getLocation().y + 4) {
                hoveredPoint = null;
                repaint();
            }
        } else {
            for (Node point : points) {
                if (arg0.getX() > point.getLocation().x - 4 && arg0.getX() < point.getLocation().x + 4
                        && arg0.getY() > point.getLocation().y - 4 && arg0.getY() < point.getLocation().y + 4) {
                    hoveredPoint = point;
                    repaint();
                }
            }
        }
        if (drawingLine) {
            xDragged = arg0.getX();
            yDragged = arg0.getY();
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {

    }

    @Override
    public void mouseExited(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent arg0) {

        if (draggingPoint && hoveredPoint != null) {
            hoveredPoint.setPosX(arg0.getX());
            hoveredPoint.setPosY(arg0.getY());
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        xReleased = arg0.getX();
        yReleased = arg0.getY();
        addKeyListener(this);

        if (draggingPoint == true) {
            draggingPoint = false;
        }
        if (selectedPoint != null && arg0.getButton() == MouseEvent.BUTTON1 && drawingLine) {
            for (Node point : points) {
                if (arg0.getX() > point.getLocation().x - 4 && arg0.getX() < point.getLocation().x + 4
                        && arg0.getY() > point.getLocation().y - 4 && arg0.getY() < point.getLocation().y + 4) {
                    Edge edg = new Edge(-1L, point, selectedPoint);
                    if (!edg.exists(edges, point, selectedPoint)) {
                        edg = edgeRepo.update(edg);
                        edges.add(edg);
                        repaint();
                        break;
                    } else {
                        System.out.println("Edge exists!");
                        break;
                    }
                }
            }
        } else if (hoveredPoint != null) {
            selectedPoint = hoveredPoint;
            try {
                simFrame.setActiveNode(selectedPoint);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (hoveredPoint != null && draggingPoint) {
            hoveredPoint = new NodeRepository().update(hoveredPoint);
        } else if (arg0.getButton() == MouseEvent.BUTTON3 && selectedPoint != null) {
            selectedPoint = null;
        } else if (arg0.getButton() == MouseEvent.BUTTON3 && selectedPoint == null) {
            try {
                Node nod = new Node();
                nod.setId(-1L);
                nod.setPosX(arg0.getX());
                nod.setPosY(arg0.getY());
                nod = nodeRepo.update(nod);
                points.add(nod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_SHIFT) {
            draggingPoint = true;
        }
        if (selectedPoint != null && ke.getKeyCode() == KeyEvent.VK_CONTROL) {
            drawingLine = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (KeyEvent.VK_SHIFT == ke.getKeyCode()) {
            draggingPoint = false;
            if (hoveredPoint != null) {
                hoveredPoint = nodeRepo.update(hoveredPoint);
            }
        } else if (KeyEvent.VK_CONTROL == ke.getKeyCode()) {
            drawingLine = false;
            repaint();
        } else if (selectedPoint != null && ke.getKeyCode() == KeyEvent.VK_DELETE) {
            for (int i = 0; i < points.size(); i++) {
                if (points.get(i).equals(selectedPoint)) {
                    points.remove(i);
                    for (int j = 0; j < edges.size(); j++) {
                        if (edges.get(j).getA().equals(selectedPoint)
                                || edges.get(j).getB().equals(selectedPoint)) {
                            edgeRepo.removeEdge(edges.get(j));
                            edges.remove(j);
                            if (!edges.isEmpty()) {
                                j = -1;
                            }
                        }
                    }
                    nodeRepo.remove(selectedPoint);
                    selectedPoint = null;
                    repaint();
                    break;
                }
            }
        }
    }

    private void initFrame(Image image) {
        setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
        setBounds(0, 0, image.getWidth(this), image.getHeight(this));
        setOpaque(false);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
    }
}
