/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.fils.smarthome.model.Edge;
import ro.fils.smarthome.model.Node;
import ro.fils.smarthome.util.DatabaseManager;

/**
 *
 * @author andre
 */
public class EdgeRepository {

    public ArrayList<Edge> getEdges(ArrayList<Node> nodes) {
        ArrayList<Edge> edges = null;
        try {
            Connection conn = DatabaseManager.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM edges");
            edges = new ArrayList<>();
            while (rs.next()) {
                Node a = null;
                Node b = null;
                for (Node current : nodes) {
                    if (current.getId() == rs.getLong("a_id")) {
                        a = current;
                    } else if (current.getId() == rs.getLong("b_id")) {
                        b = current;
                    }
                }
                edges.add(new Edge(rs.getLong("id"), a, b));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EdgeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return edges;
    }

    public void insertEdge(Edge edge) {
        try {
            Connection conn = DatabaseManager.getConnection();
            if (edge.getId() == -1) {
                PreparedStatement stm = conn.prepareStatement("INSERT INTO edges (a_id,b_id) VALUES(?,?)");
                stm.setLong(1, edge.getA().getId());
                stm.setLong(0, edge.getB().getId());
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EdgeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeEdge(Edge edge) {
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "DELETE FROM edges WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, edge.getId());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EdgeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Edge update(Edge edge) {
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "INSERT INTO edges(a_id, b_id) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            if (edge.getId() == -1) {
                ps.setLong(1,edge.getA().getId());
                ps.setLong(2, edge.getB().getId());
                edge.setId(DatabaseManager.getLastId(conn));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return edge;
    }
    
    
}
