package main.java.com.example.server.dataAccesses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ConnectDAO {
    
    private Connection theConnection;

    public ConnectDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS connections (userid1 INT NOT NULL, userid2 INT NOT NULL);");
        statement.executeUpdate();
    }

    public void createConnection(int userid1, int userid2) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO connections (userid1, userid2) VALUES (?, ?);");
        statement.setInt(1, userid1);
        statement.setInt(2, userid2);
        statement.executeUpdate();
    }

    public void deleteConnection(int userid1, int userid2) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM connectiond WHERE (userid1 = ? AND userid2 = ?) OR (userid2 = ? AND userid1 = ?);");
        statement.setInt(1, userid1);
        statement.setInt(2, userid2);
        statement.setInt(3, userid1);
        statement.setInt(4, userid2);
        statement.executeUpdate();
    }

}
