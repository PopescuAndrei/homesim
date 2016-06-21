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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.fils.smarthome.model.Agent;
import ro.fils.smarthome.model.Need;
import ro.fils.smarthome.util.DatabaseManager;

/**
 *
 * @author andre
 */
public class AgentRepository {

    public ArrayList<Agent> getAgentsForScenario(int scenarioId) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        ArrayList<Agent> agents = new ArrayList<>();
        try {
            conn = DatabaseManager.getConnection();
            sql = "SELECT * FROM agents INNER JOIN scenarios_agents ON agents.id = scenarios_agents.agent_id WHERE scenarios_agents.scenario_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, scenarioId);
            ResultSet agentsRes = ps.executeQuery();
            while (agentsRes.next()) {
                Agent ag = new Agent(agentsRes.getString("AGENT_NAME"), agentsRes.getString("AVATAR_IMG"), new Point(agentsRes.getInt("LOC_X"), agentsRes.getInt("LOC_Y")), getNeedsForAgent(agentsRes.getInt("ID")));
                ag.setId(agentsRes.getInt("ID"));
                agents.add(ag);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AgentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agents;
    }

    public boolean saveAgentsToScenario(Agent ag, int scenarioId) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        boolean agentSaved = false;
        try {
            conn = DatabaseManager.getConnection();
            sql = "INSERT INTO AGENTS(AGENT_NAME,AVATAR_IMG,LOC_X,LOC_Y) VALUES(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ag.getName());
            ps.setString(2, ag.getImagePath());
            ps.setInt(3, (int) ag.getCurrentLocation().getX());
            ps.setInt(4, (int) ag.getCurrentLocation().getY());
            ps.execute();
            agentSaved = true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AgentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (agentSaved) {
            int agentId = getLastAgentInsertedId();
            if (saveNeedsForAgent(ag.getNeeds(), agentId)) {
                return true;
            } else {
                deleteAgentById(agentId);
                return false;
            }
        }
        return false;
    }

    public boolean deleteAgentById(int agentId) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        try {
            conn = DatabaseManager.getConnection();
            sql = "DELETE FROM agents WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, agentId);
            ps.execute();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AgentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean saveNeedsForAgent(List<Need> needs, int agentId) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        try {
            for (Need n : needs) {
                conn = DatabaseManager.getConnection();
                sql = "INSERT INTO agent_needs(NEED_ID, DECAY_RATE, AGENT_ID) VALUES (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, n.getId());
                ps.setDouble(2, n.getDecayRate());
                ps.setInt(3, agentId);
                ps.execute();
            }
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AgentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private List<Need> getNeedsForAgent(int agentId) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        ArrayList<Need> needs = null;
        try {
            conn = DatabaseManager.getConnection();
            sql = "SELECT * FROM agent_needs WHERE agent_id = ? ORDER BY id";
            needs = new ArrayList<>();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, agentId);
            ResultSet needsRes = ps.executeQuery();
            while (needsRes.next()) {
                Need n;
                switch (needsRes.getInt("need_id")) {
                    case 1:
                        n = new Need("Energy", needsRes.getDouble("decay_rate"));
                        break;
                    case 2:
                        n = new Need("Hunger", needsRes.getDouble("decay_rate"));
                        break;
                    case 3:
                        n = new Need("Bladder", needsRes.getDouble("decay_rate"));
                        break;
                    case 4:
                        n = new Need("Hygiene", needsRes.getDouble("decay_rate"));
                        break;
                    default:
                        n = new Need("Fun", needsRes.getDouble("decay_rate"));
                        break;
                }
                needs.add(n);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AgentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return needs;
    }

    public int getLastAgentInsertedId() {
        Connection conn;
        Statement st;
        String sql = "SELECT MAX(id) AS id FROM agents";
        int id = 0;
        try {
            conn = DatabaseManager.getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ScenarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
