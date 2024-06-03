package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.ParticipatedOrginazation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ParticipatedOrginazationDAO {
    
    private Connection theConnection;

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS participatedorganazations (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NUO NULL, institution VARCHAR(40), position VARCHAR(40), startdate TIMESTAMP, enddate TIMESTAMP, stillworking BOOLEAN0);");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE participatedorganazations AUTO_INCREMENT = 45000000;");
    }

    public void saveParticipatedOrginazation(ParticipatedOrginazation participatedorganazation) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO participatedorganazations (userid, institution, position, startdate, enddate, stillworking) VALUES (?, ?, ?, ?, ?, ?);");
        statement.setInt(1, participatedorganazation.getUserId());
        statement.setString(2, participatedorganazation.getInstitution());
        statement.setString(3, participatedorganazation.getPosition());
        statement.setTimestamp(4, participatedorganazation.getStartDate());
        statement.setTimestamp(5, participatedorganazation.getEndDate());
        statement.setBoolean(6, participatedorganazation.isStillWorking());
        statement.executeUpdate();
    }

    public void updateParticipatedOrginazation(ParticipatedOrginazation participatedorganazation) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE participatedorganazations SET userid = ?, institution = ?, position = ?, startdate = ?, enddate = ?, stillworking = ? WHERE id = ?;");
        statement.setInt(1, participatedorganazation.getUserId());
        statement.setString(2, participatedorganazation.getInstitution());
        statement.setString(3, participatedorganazation.getPosition());
        statement.setTimestamp(4, participatedorganazation.getStartDate());
        statement.setTimestamp(5, participatedorganazation.getEndDate());
        statement.setBoolean(6, participatedorganazation.isStillWorking());
        statement.setInt(7, participatedorganazation.getId());
        statement.executeUpdate();
    }
    
}
