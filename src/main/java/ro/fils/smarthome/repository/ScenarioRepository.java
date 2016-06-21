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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.fils.smarthome.model.Scenario;
import ro.fils.smarthome.util.DatabaseManager;

/**
 *
 * @author andre
 */
public class ScenarioRepository {

    public List<Scenario> getAllScenarios() {
        List<Scenario> scenarios = null;
        Connection conn;
        Statement st;
        String sql;
        try {
            conn = DatabaseManager.getConnection();
            sql = "SELECT * FROM scenarios";
            st = conn.createStatement();
            ResultSet sRes = st.executeQuery(sql);
            scenarios = new ArrayList<>();
            while (sRes.next()) {
                Scenario s = new Scenario();
                s.setName(sRes.getString("NAME"));
                s.setSimsPerSec(sRes.getInt("SIMS_PER_SEC"));
                s.setId(sRes.getInt("ID"));
                s.setHouseId(sRes.getInt("HOUSE_ID"));
                s.setSensorFile(sRes.getString("SENSOR_FILE"));
                s.setTaskile(sRes.getString("ACTIVITIES_FILE"));
                s.setStartingPoint(sRes.getLong("STARTING_POINT"));
                s.setWalking_speed(sRes.getInt("WALKING_SPEED"));
                scenarios.add(s);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ScenarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scenarios;
    }

    public Scenario getScenarioByName(String scenarioName) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        Scenario s = null;
        try {
            conn = DatabaseManager.getConnection();
            sql = "SELECT * FROM scenarios INNER JOIN HOUSES ON scenarios.house_id = houses.id WHERE name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, scenarioName);
            ResultSet sRes = ps.executeQuery();
            s = new Scenario();
            while (sRes.next()) {
                s.setSimsPerSec(sRes.getInt("SIMS_PER_SEC"));
                s.setId(sRes.getInt("ID"));
                s.setHouseId(sRes.getInt("HOUSE_ID"));
                s.setSensorFile(sRes.getString("SENSOR_FILE"));
                s.setTaskile(sRes.getString("ACTIVITIES_FILE"));
                s.setStartingPoint(sRes.getLong("STARTING_POINT"));
                s.setWalking_speed(sRes.getInt("WALKING_SPEED"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ScenarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public void saveScenario(Scenario s) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        try {
            conn = DatabaseManager.getConnection();
            sql = "INSERT INTO SCENARIOS (NAME,ACTIVITIES_FILE,HOUSE_ID,SENSOR_FILE,STARTING_POINT,SIMS_PER_SEC,WALKING_SPEED) VALUES (?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setString(2, s.getTaskile());
            ps.setInt(3, s.getHouseId());
            ps.setString(4, s.getSensorFile());
            ps.setLong(5, s.getStartingPoint());
            ps.setInt(6, s.getSimsPerSec());
            ps.setInt(7, s.getWalking_speed());
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ScenarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getLastInsertedScenarioId() {
        Connection conn;
        Statement st;
        String sql = "SELECT MAX(id) AS id FROM scenarios";
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

    public void addAgentToScenario(int lastAgentInsertedId, int lastInsertedScenarioId) {
        Connection conn;
        PreparedStatement ps;
        String sql = "INSERT INTO SCENARIOS_AGENTS(SCENARIO_ID, AGENT_ID) VALUES (?,?)";
        try {
            conn = DatabaseManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, lastInsertedScenarioId);
            ps.setInt(2, lastAgentInsertedId);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ScenarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
