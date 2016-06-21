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
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.fils.smarthome.model.House;
import ro.fils.smarthome.util.DatabaseManager;

/**
 *
 * @author andre
 */
public class HouseRepository {

    public String getHouseFileByHouseId(int houseId) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        try {
            conn = DatabaseManager.getConnection();
            sql = "SELECT * FROM HOUSES WHERE ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, houseId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getString("PATH");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public House getHouseByFileName(String path) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        House h = null;
        try {
            conn = DatabaseManager.getConnection();
            sql = "SELECT * FROM HOUSES WHERE PATH = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, path);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                h = new House();
                h.setId(rs.getInt("ID"));
                h.setPath(rs.getString("PATH"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return h;
    }
    
     public void saveHouse(House house) {
        Connection conn;
        PreparedStatement ps;
        String sql;
        try {
            conn = DatabaseManager.getConnection();
            sql = "INSERT INTO HOUSES (PATH) VALUES (?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, house.getPath());
            ps.executeQuery();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
