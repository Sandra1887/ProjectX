package org.example;

import java.sql.SQLException;

public class Application {
    ToDo todo;
    DbHandler dbHandler;
    Helper helper;
    boolean exit = false;

    public Application() throws SQLException {
        helper = new Helper();
        String dbName = helper.askForDbName();
        dbHandler = new DbHandler(dbName);
    }
    public void start() {
        while (!exit) {
            switch (helper.mainMenu()) {
                case 1 -> {
                    dbHandler.create();
                    break;
                }
                case 2 -> {
                    dbHandler.read();
                    break;
                }
                case 3 -> {
                    dbHandler.update();
                    break;
                }
                case 4 -> {
                    dbHandler.delete();
                }
                case 5 -> {
                    exit = true;
                }
            }
        }
    }
}
