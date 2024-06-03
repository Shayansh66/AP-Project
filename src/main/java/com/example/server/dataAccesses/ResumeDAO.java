package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Resume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ResumeDAO {
    
    private Connection theConnection;

    public ResumeDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }
    
    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS resumes (id INT PRIMARY KEY AUTO_INCREMENT, ownerid INT NOT NULL, positionid INT NOT NULL, resumefile VARCHAR(255), description VARCHAR(500));");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE resumes AUTO_INCREMENT = 42000000;");
        statement.executeUpdate();
    }

    

}
