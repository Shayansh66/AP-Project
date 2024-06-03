package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.ParticipatedOrginazation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


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

    public void deleteParticipatedOrginazation(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM participatedorganazations WHERE id = ?;");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void deleteParticipatedOrginazation(ParticipatedOrginazation participatedOrginazation) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM participatedorganazations WHERE id = ?;");
        statement.setInt(1, participatedOrginazation.getId());
        statement.executeUpdate();
    }

    public void deleteParticipatedOrginazations() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DLETE FROM participatedorganazations;");
        statement.executeUpdate();
    }

    public ParticipatedOrginazation getParticipatedOrginazation(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM participatedorganazations WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            ParticipatedOrginazation participatedOrginazation = new ParticipatedOrginazation();
            participatedOrginazation.setId(resultSet.getInt("id"));
            participatedOrginazation.setUserId(resultSet.getInt("userid"));
            participatedOrginazation.setInstitution(resultSet.getString("institution"));
            participatedOrginazation.setPosition(resultSet.getString("position"));
            participatedOrginazation.setStartDate(resultSet.getTimestamp("pstrtdate"));
            participatedOrginazation.setEndDate(resultSet.getTimestamp("enddate"));
            participatedOrginazation.setStillWorking(resultSet.getBoolean("stillworking"));
            return participatedOrginazation;
        }

        return null;
    }

    public ArrayList<ParticipatedOrginazation> getParticipatedOrginazationsByUserId(int userid) throws SQLException {
        ArrayList<ParticipatedOrginazation> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM participatedorganazations WHERE userid = ?;");
        statement.setInt(1, userid);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            ParticipatedOrginazation participatedOrginazation = new ParticipatedOrginazation();
            participatedOrginazation.setId(resultSet.getInt("id"));
            participatedOrginazation.setUserId(resultSet.getInt("userid"));
            participatedOrginazation.setInstitution(resultSet.getString("institution"));
            participatedOrginazation.setPosition(resultSet.getString("position"));
            participatedOrginazation.setStartDate(resultSet.getTimestamp("pstrtdate"));
            participatedOrginazation.setEndDate(resultSet.getTimestamp("enddate"));
            participatedOrginazation.setStillWorking(resultSet.getBoolean("stillworking"));
            list.add(participatedOrginazation);
        }

        return list;
    }
    
    public ArrayList<ParticipatedOrginazation> getParticipatedOrginazations() throws SQLException {
        ArrayList<ParticipatedOrginazation> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM participatedorganazations;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            ParticipatedOrginazation participatedOrginazation = new ParticipatedOrginazation();
            participatedOrginazation.setId(resultSet.getInt("id"));
            participatedOrginazation.setUserId(resultSet.getInt("userid"));
            participatedOrginazation.setInstitution(resultSet.getString("institution"));
            participatedOrginazation.setPosition(resultSet.getString("position"));
            participatedOrginazation.setStartDate(resultSet.getTimestamp("pstrtdate"));
            participatedOrginazation.setEndDate(resultSet.getTimestamp("enddate"));
            participatedOrginazation.setStillWorking(resultSet.getBoolean("stillworking"));
            list.add(participatedOrginazation);
        }

        return list;
    }
    
}
