/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.repository;

import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.fils.smarthome.model.Appliance;
import ro.fils.smarthome.model.Edge;
import ro.fils.smarthome.model.Node;
import ro.fils.smarthome.util.DatabaseManager;

/**
 *
 * @author andre
 */
public class NodeRepository {

    public ArrayList<Node> getNodes() {
        Connection conn;
        PreparedStatement ps;
        String sql;
        ArrayList<Node> nodes = new ArrayList<>();
        try {
            conn = DatabaseManager.getConnection();
            sql = "SELECT * FROM nodes";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Node nod = new Node(rs.getLong("id"),
                        new Point(rs.getInt("posX"), rs.getInt("posY")));
                ArrayList<Appliance> types = new ArrayList<>();

                sql = "SELECT * FROM appliances WHERE node_id = ?";
                ps = conn.prepareStatement(sql);
                ps.setLong(1, rs.getLong("id"));
                ResultSet appRes = ps.executeQuery();
                while (appRes.next()) {
                    String poses = appRes.getString("poses");
                    if (poses != null) {
                        String[] poseVals = poses.split(" ");
                        Set<String> poseSet = new HashSet<>(Arrays.asList(poseVals));
                        types.add(new Appliance(appRes.getString("type"), nod, poseSet));
                    } else {
                        types.add(new Appliance(appRes.getString("type"), nod));
                    }
                }
                nod.setApplianceTypes(types);
                nodes.add(nod);
            }
            ArrayList<Edge> edges = new EdgeRepository().getEdges(nodes);
            for (Node node : nodes) {
                for (Edge edge : edges) {
                    if (Objects.equals(node.getId(), edge.getA().getId())) {
                        node.addNeighbor(edge.getB());
                    } else if (Objects.equals(node.getId(), edge.getB().getId())) {
                        node.addNeighbor(edge.getA());
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
        return nodes;
    }

    public boolean remove(Node node) {
        if (node.getId() == -1) {
            return false;
        }
        Connection conn;
        PreparedStatement ps;
        String sql;
        try {
            conn = DatabaseManager.getConnection();
            sql = "DELETE FROM nodes WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, node.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Node update(Node node) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        try {
            conn = DatabaseManager.getConnection();
            if (node.getId() == -1) {
                sql = "INSERT INTO nodes (posX, posY) VALUES (?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, node.getPosX());
                ps.setInt(2, node.getPosY());
                node.setId(DatabaseManager.getLastId(conn));
                return node;
            } else {
                sql = "UPDATE nodes SET posX = ?, posY = ? WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, node.getPosX());
                ps.setInt(2, node.getPosY());
                ps.setLong(3, node.getId());
                ps.executeUpdate();
            }
            sql = "DELETE from appliances WHERE node_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, node.getId());
            ps.executeUpdate();
            for (Appliance appl : node.getApplianceTypes()) {
                sql = "INSERT INTO appliances (node_id, type, poses) VALUES (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setLong(1, node.getId());
                ps.setString(2, appl.getType());
                ps.setString(3, "");
                ps.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return node;
    }

    public Appliance updatePoses(Node node, Appliance app, String poseString) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        try {
            conn = DatabaseManager.getConnection();
            sql = "UPDATE appliances SET poses = ? WHERE node_id = ? AND type =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, poseString);
            ps.setLong(2, node.getId());
            ps.setString(3, app.getType());
            app.setPoses(poseString);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return app;
    }

    public Node getNodeById(Long nodeId) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        Node node = null;
        try {
            conn = DatabaseManager.getConnection();
            sql = "SELECT * FROM nodes WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, nodeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Node nod = new Node(rs.getLong("id"),
                        new Point(rs.getInt("posX"), rs.getInt("posY")));
                ArrayList<Appliance> types = new ArrayList<>();

                sql = "SELECT * FROM appliances WHERE node_id = ?";
                ps = conn.prepareStatement(sql);
                ps.setLong(1, rs.getLong("id"));
                ResultSet appRes = ps.executeQuery();
                while (appRes.next()) {
                    String poses = appRes.getString("poses");
                    if (poses != null) {
                        String[] poseVals = poses.split(" ");
                        Set<String> poseSet = new HashSet<>(Arrays.asList(poseVals));
                        types.add(new Appliance(appRes.getString("type"), nod, poseSet));
                    } else {
                        types.add(new Appliance(appRes.getString("type"), nod));
                    }
                }
                nod.setApplianceTypes(types);
                return nod;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(NodeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
