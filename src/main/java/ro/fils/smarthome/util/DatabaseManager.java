/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.util;

import ro.fils.smarthome.util.support.DatabaseProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class DatabaseManager {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/homesim";
    private static final String CONNECTION_CHECK_QUERY = "SELECT 1 FROM DUAL";
    private static boolean isDriverRegistered = false;

    /**
     * Execution of default constructor is not allowed
     */
    private DatabaseManager() {
        throw new UnsupportedOperationException();
    }

    private static void registerDriver() throws ClassNotFoundException {
        if (!isDriverRegistered) {
            Class.forName(DatabaseProperties.DRIVER_CLASS);
            isDriverRegistered = true;
        }
    }

    /**
     * Create a new connection
     *
     * @return - A new Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        registerDriver();

        return DriverManager.getConnection(
                CONNECTION_STRING,
                DatabaseProperties.USER,
                DatabaseProperties.PASS);
    }

    /**
     * Check if a connection is active
     *
     * @param connection - A connection
     * @return - true if the connection is active
     */
    public static boolean checkConnection(Connection connection) {
        boolean result = false;
        try (Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(CONNECTION_CHECK_QUERY)) {
            if (rs.next()) {
                result = rs.getLong(1) == 1l;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Used to get the last inserted id in the db
     * @param conn the connection to the database
     * @return the last inserted Id
     */
    public static Long getLastId(Connection conn) {
        Long lastId = null;
        PreparedStatement getLastInsertId;
        try {
            getLastInsertId = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rs = getLastInsertId.executeQuery();
            if (rs.next()) {
                lastId = rs.getLong("last_insert_id()");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastId;
    }
}
