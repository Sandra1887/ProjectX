package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public interface iDb {
    public Connection getConnection(String dbName) throws SQLException;
    public void setConnection(Connection connection);
}
