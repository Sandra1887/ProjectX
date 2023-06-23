package org.example;

import java.sql.*;

public class DbHandler implements iCrud {
    private Connection connection;
    private Statement stmt;
    private ResultSet rs;
    private Helper helper;
    private ToDo todo;

    public DbHandler(String dbName) throws SQLException {
        getConnection(dbName);
        helper = new Helper();
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

    @Override
    public boolean create() {
        String tableName = helper.askForTableName();

        if(searchForTable(tableName) == true) {
            ToDo todo = helper.askForAssignment();
            String sql = "INSERT INTO " + tableName + " (assignment, assignee, done) VALUES (?,?,?)"; // plus för att du har paramtrar!

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, todo.getAssignment());
                pstmt.setString(2, todo.getAssignee());
                pstmt.setString(3, todo.getDone());
                pstmt.executeUpdate();
                return true; //assignment added
            } catch (SQLException e) {
                System.out.println("Error creating todo: " + e.getMessage());
                return false; //assignment not added
            }
        } if (searchForTable(tableName) == false) {
            createTable(tableName); //skapa en ny tabell
            create();
        }
        return true;
    }
    @Override
    public boolean read() {
        String tableName = helper.askForTableName();
        String sql = "SELECT * FROM " + tableName;
        boolean result = false;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String assignment = rs.getString("assignment");
                String assignee = rs.getString("assignee");
                String done = rs.getString("done");
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Error reading table: " + e.getMessage());
            result = false;
        }
        return result;
    }

    @Override
    public boolean update() {
        String tableName = helper.askForTableName();
        int todoId = helper.askForId();
        String asOrDo = helper.askForUpdate();

        if (asOrDo.equalsIgnoreCase("assignment")) {
            String sql = "UPDATE " + tableName + " SET assignment = ? WHERE todo_id = ?";
            String assignment = helper.askForOnlyAssignment();
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, assignment);
                pstmt.setInt(2, todoId);
                pstmt.executeUpdate();
                return false;
            } catch (SQLException e) {
                System.out.println("Error updating table: " + e.getMessage());
                return true;
            }
        }
        if(asOrDo.equalsIgnoreCase("done")) {
            String sql = "UPDATE " + tableName + " SET done = ? WHERE todo_id = ?";
            String done = helper.askForDone();
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, done);
                pstmt.setInt(2, todoId);
                pstmt.executeUpdate();
                return false;
            } catch (SQLException e) {
                System.out.println("Error updating table: " + e.getMessage());
                return true;
            }
        } else System.out.println("Wrong input!");
        return false;
    }

    @Override
    public boolean delete() {
        String tableName = helper.askForTableName();
        String sql = "DROP TABLE " + tableName;
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println("Error dropping table: " + e.getMessage());
            return false;
        }
    }

    public void createTable(String tableName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "todo_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "assignment VARCHAR(50)," +
                "assignee VARCHAR(50)," +
                "done VARCHAR(10)" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
    public boolean searchForTable(String tableName) {
        try {
            stmt = connection.createStatement();
            String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "'";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return true; //table exists
            } else {
                return false; //Table does not exist
            }
        } catch (SQLException e) {
            System.out.println("Error message: " + e.getMessage());
        }
        return false;
    }
}
