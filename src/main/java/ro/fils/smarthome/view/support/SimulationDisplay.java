/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.view.support;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.sensors.Contact;
import ro.fils.smarthome.sensors.Sensor;
import ro.fils.smarthome.sensors.SensorArea;

/**
 *
 * @author andre
 */
public class SimulationDisplay extends JPanel {

    private Image image;
    private Collection<Sensor> sensors;
    private Collection<Agent> people;

    public SimulationDisplay(String mapName) {
        image = new ImageIcon(getClass().getResource("/environment.jpg").getPath()).getImage();
        initFrame(image);
        people = null;
        sensors = null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
        if (people != null) {
            for (Agent agent : people) {
                g.drawImage(agent.getAvatarImg(), agent.getCurrentLocation().x - (agent.getAvatarImg().getWidth(this) / 2),
                        agent.getCurrentLocation().y - (agent.getAvatarImg().getHeight(this) / 2),
                        agent.getAvatarImg().getWidth(this), agent.getAvatarImg().getHeight(this), this);
            }
        }
        if (sensors != null) {
            for (Sensor sensor : sensors) {
                if (!(sensor instanceof Contact)) {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(sensor.getPosition().x - 1, sensor.getPosition().y - 1,
                            2, 2);
                    for (SensorArea s : sensor.getSensorAreas()) {
                        boolean steppedOn = false;
                        if (people != null) {
                            for (Agent p : people) {
                                if (s.getArea().contains(p.getCurrentLocation())) {
                                    steppedOn = true;
                                }
                            }
                        }
                        if (steppedOn) {
                            g2d.setColor(new Color(255, 102, 82, 70));
                        } else {
                            g2d.setColor(new Color(0, 188, 212, 20));
                        }
                        g2d.fill(s.getArea());
                        g2d.setColor(Color.BLACK);
                        g2d.draw(s.getArea());
                    }
                }
            }
        }
    }

    public void update(Collection<Agent> people, Collection<Sensor> sensors) {
        this.people = people;
        this.sensors = sensors;
        repaint();
    }

    private void initFrame(Image image) {
        setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
        setBounds(0, 0, image.getWidth(this), image.getHeight(this));
        setOpaque(false);
        setFocusable(true);
    }
}
