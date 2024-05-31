package main.java.com.example.server.dataAccesses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ContactDAO {
    
    private Connection thConnection;

    public ContactDAO() throws SQLException {
        thConnection = DatabaseConnectionManager.getTheConnection();
        creatTable();
    }

    public void creatTable() throws SQLException {
        PreparedStatement statement = thConnection.prepareStatement("CREATE TABLE IF NOT EXISTS contacts (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOT NULL, profilelink VARCHAR(255), email VARCHAR(255), phonenumber VARCHAR(40), phonetype VARCHAR(40), address VARCHAR(220), birthday TIMESTAMP, birthdayvisibility VARCHAR(40), communicationid VARCHAR(255);");
        statement.executeUpdate();
        statement = thConnection.prepareStatement("ALTER TABLE contacts AUTO_INCREMENT = 46000000;");
        statement.executeUpdate();
    }
    
}
