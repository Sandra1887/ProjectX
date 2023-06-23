package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHandler {
    private Connection connection;

    public DbHandler(String dbName) throws SQLException {
        getConnection(dbName);
    }
    
    public Connection getConnection(String dbName) throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
            } catch (SQLException e) {
                System.out.println("Error connecting to database: " + e.getMessage());
            }
        }
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
