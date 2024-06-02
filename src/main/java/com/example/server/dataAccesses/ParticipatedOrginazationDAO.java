package main.java.com.example.server.dataAccesses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ParticipatedOrginazationDAO {
    
    private Connection theConnection;

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS participatedorganazation (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NUO NULL, institution VARCHAR(40), position VARCHAR(40), startdate TIMESTAMP, enddate TIMESTAMP, stillworking BOOLEAN)");
    }
    
}
