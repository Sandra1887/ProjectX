package org.example;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DbHandlerTest {

    Connection connectionMock;
    DbHandler dbHandlermock;
    String dbName = "testDbName";
    @Test
    public void testConnection() throws SQLException {
        //create a mock connection object
        connectionMock = mock(Connection.class);
        //specify the behavior of the mock connection
        when(connectionMock.isValid(0)).thenReturn(true);
        //Create an instance of the DbHandler
        dbHandlermock = new DbHandler(dbName);
        //set the mock connection
        dbHandlermock.setConnection(connectionMock);
        //Retrieve the information and perform tests
        Connection connection = dbHandlermock.getConnection(dbName);
        //Verify the behavior of the mocked connection
        assertTrue(connection.isValid(0));
    }
}