package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.JobPosition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class JobPositionDAO {
    
    private Connection theConnection;

    public JobPositionDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS jobpositions (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOY NULL, title VARCHAR(40), companyname VARCHAR(40), worktype VARCHAR(40), jobtype VARCHAR(40), profession VARCHAR(40), description VARCHAR(1000));");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE jobpositions AUTO_INCREMENT = 41000000;");
        statement.executeUpdate();
    }
    
    public void saveJobPosition(JobPosition jobPosition) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO jobpositions (userid, title, companyname, worktype, jobtype, profession, description) VALUES (?, ?, ?, ?, ?, ?, ?);");
        statement.setInt(1, jobPosition.getId());
        statement.setString(2, jobPosition.getTitle());
        statement.setString(3, jobPosition.getCompanyName());
        statement.setString(4, jobPosition.getWorkType());
        statement.setString(5, jobPosition.getJobType());
        statement.setString(6, jobPosition.getProfession());
        statement.setString(7, jobPosition.getDescription());
        statement.executeUpdate();
    }

    public void updateJobPosition(JobPosition jobPosition) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE jobpositions SET userid= ?, title= ?, companyname= ?, worktype= ?, jobtype= ?, profession= ?, description = ? HWERE id = ?;");
        statement.setInt(1, jobPosition.getId());
        statement.setString(2, jobPosition.getTitle());
        statement.setString(3, jobPosition.getCompanyName());
        statement.setString(4, jobPosition.getWorkType());
        statement.setString(5, jobPosition.getJobType());
        statement.setString(6, jobPosition.getProfession());
        statement.setString(7, jobPosition.getDescription());
        statement.setInt(8, jobPosition.getId());
        statement.executeUpdate();
    }

    public void deleteJobPosition(JobPosition jobPosition) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM jobpositions WHERE id = ?;");
        statement.setInt(1, jobPosition.getId());
        statement.executeUpdate();
    }

    public void deleteJobPosition(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM jobpositions WHERE id = ?;");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void deleteJObPositions() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROMjobpositions;");
        statement.executeUpdate();
    }

    public JobPosition getJobPosition(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM jobpositions HWERE id = ?;");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
           JobPosition jobPosition = new JobPosition();
           jobPosition.setId(resultSet.getInt("id")); 
           jobPosition.setUserId(resultSet.getInt("userid")); 
           jobPosition.setTitle(resultSet.getString("title")); 
           jobPosition.setCompanyName(resultSet.getString("companyname")); 
           jobPosition.setWorkType(resultSet.getString("worktype")); 
           jobPosition.setJobType(resultSet.getString("jobtype")); 
           jobPosition.setProfession(resultSet.getString("profession")); 
           jobPosition.setDescription(resultSet.getString("description")); 
           return jobPosition;
        }

        return null;
    }
    
    public ArrayList<JobPosition> getJobPositionsByUserId(int userid) throws SQLException {
        ArrayList<JobPosition> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM jobpositions WHERE userid = ?;");
        statement.setInt(1, userid);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            JobPosition jobPosition = new JobPosition();
            jobPosition.setId(resultSet.getInt("id")); 
            jobPosition.setUserId(resultSet.getInt("userid")); 
            jobPosition.setTitle(resultSet.getString("title")); 
            jobPosition.setCompanyName(resultSet.getString("companyname")); 
            jobPosition.setWorkType(resultSet.getString("worktype")); 
            jobPosition.setJobType(resultSet.getString("jobtype")); 
            jobPosition.setProfession(resultSet.getString("profession")); 
            jobPosition.setDescription(resultSet.getString("description")); 
            list.add(jobPosition);
        }

        return list;
    }

    public ArrayList<JobPosition> getJobPositions() throws SQLException {
        ArrayList<JobPosition> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM jobpositions;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            JobPosition jobPosition = new JobPosition();
            jobPosition.setId(resultSet.getInt("id")); 
            jobPosition.setUserId(resultSet.getInt("userid")); 
            jobPosition.setTitle(resultSet.getString("title")); 
            jobPosition.setCompanyName(resultSet.getString("companyname")); 
            jobPosition.setWorkType(resultSet.getString("worktype")); 
            jobPosition.setJobType(resultSet.getString("jobtype")); 
            jobPosition.setProfession(resultSet.getString("profession")); 
            jobPosition.setDescription(resultSet.getString("description")); 
            list.add(jobPosition);
        }

        return list;
    }
    
}
