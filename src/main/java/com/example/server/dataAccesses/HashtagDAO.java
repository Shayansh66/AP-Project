package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Hashtag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class HashtagDAO {
    
    private Connection theConnection;

    public HashtagDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }
    
    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CRAATE TABLE IF NOT EXISTS hashtags (id INT PRIMARY KEY AUTO_INCREMENT, content VARCHAR(255));");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE hashtags AUTO_INCREMENT = 26000000;");
        statement.executeUpdate();
    }
    
}
