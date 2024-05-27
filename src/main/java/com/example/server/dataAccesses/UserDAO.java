package main.java.com.example.server.dataAccesses;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class UserDAO {

    private Connection theConnection;

    public UserDAO() throws SQLException {
        this.theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id PRIMARY KEY AUTO_INCREMENT, firstname VARCHAR(20), lastname VARCHAR(40), additionalname VARCHAR(40), headtitle VARCHAR(220), countrey VARCHAR(60), city VARCHAR(60), createdate TIMESTAMP DEFAULT NOW())");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE users AUTO_INCREMENT = 20000000");
        statement.executeUpdate();
    }
    
}
