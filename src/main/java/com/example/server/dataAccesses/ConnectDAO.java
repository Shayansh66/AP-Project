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
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS connections (sender INT NOT NULL, reciever INT NOT NULL);");
        statement.executeUpdate();
    }

    public void requestConnection(int sender, int reciever) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO connections (sender, reciever) VALUES (?, ?);");
        statement.setInt(2, sender);
        statement.setInt(2, reciever);
        statement.executeUpdate();
    }

    public boolean isRequestSent(int sender, int reciever) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM connections WHERE (sender = ? AND reciever = ?);");
        statement.setInt(1, sender);
        statement.setInt(2, reciever);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() == false) {
            return false;
        }
        
        statement = theConnection.prepareStatement("SELECT * FROM connections WHERE (reciever = ? AND sender = ?);");
        statement.setInt(1, sender);
        statement.setInt(2, reciever);
        resultSet = statement.executeQuery();
        if (resultSet.next() == false) {
            return true;
        }

        return false;
    }

    public void acceptConnection(int sender, int reciever) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO connections (sender, reciever) VALUES (?, ?);");
        statement.setInt(2, sender);
        statement.setInt(2, reciever);
        statement.executeUpdate();
    }

    public void deleteConnection(int user1, int user2) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM connections WHERE (sender = ? AND reciever = ?) OR (reciever = ? AND sender = ?);");
        statement.setInt(1, user1);
        statement.setInt(2, user2);
        statement.setInt(3, user1);
        statement.setInt(4, user2);
        statement.executeUpdate();
    }

    public boolean isConnected(int user1, int user2) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM connections WHERE (sender = ? AND reciever = ?);");
        statement.setInt(1, user1);
        statement.setInt(2, user2);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() == false) {
            return false;
        }
        
        statement = theConnection.prepareStatement("SELECT * FROM connections WHERE (reciever = ? AND sender = ?);");
        statement.setInt(1, user1);
        statement.setInt(2, user2);
        resultSet = statement.executeQuery();
        if (resultSet.next() == false) {
            return false;
        }

        return true;
    }

}
