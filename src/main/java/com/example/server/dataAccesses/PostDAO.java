package main.java.com.example.server.dataAccesses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PostDAO {

    private Connection theConnection;

    public PostDAO() throws SQLException {
        this.theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    private void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS posts (id INT PRIMARY KEY AUTO_INCREMENT, writterid INT NOT NULL, content VARCHAR(3000), likenumber INT DEFAULT 0, commentnumber INT DEFAULT 0, createdate TIMESTAMP DEFAULT NOW())");
        statement.executeUpdate();
    }
    
}
