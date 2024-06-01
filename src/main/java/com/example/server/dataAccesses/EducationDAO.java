package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Education;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class EducationDAO {
    
    private Connection theConnection;

    public EducationDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS educations (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOT NULL, institutionname VARCHAR(40), studyfield VARCHAR(40), startdate TIMESTAMP, enddate TIMESTAMP, grade DECIMAL(2, 2), descriptionofactivities VARCHAR(500), description VARCHAR(1000));");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE educations AUTO_INCREMENT = 42000000;");
        statement.executeUpdate();
    }

    public void saveEducation(Education education) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO educations (userid, instutationname, studyfield, startdate, enddate, grade, descriptionofactivities, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
        statement.setInt(1, education.getuserid());
        statement.setString(2, education.getInstitutionName());
        statement.setString(3, education.getStudyField());
        statement.setTimestamp(4, education.getStartDate());
        statement.setTimestamp(5, education.getEndDate());
        statement.setDouble(6, education.getGrade());
        statement.setString(7, education.getDescriptionOfActivities());
        statement.setString(8, education.getDescription());
        statement.executeUpdate();
    }

    public void updateEducation(Education education) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE educations SET userid = ?, instutationname = ?, studyfield = ?, startdate = ?, enddate = ?, grade = ?, descriptionofactivities = ?, description = ? WHERE id = ?;");
        statement.setInt(1, education.getuserid());
        statement.setString(2, education.getInstitutionName());
        statement.setString(3, education.getStudyField());
        statement.setTimestamp(4, education.getStartDate());
        statement.setTimestamp(5, education.getEndDate());
        statement.setDouble(6, education.getGrade());
        statement.setString(7, education.getDescriptionOfActivities());
        statement.setString(8, education.getDescription());
        statement.setInt(9, education.getId());
        statement.executeUpdate();
    }

    public void deleteEducation(String id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM educations WHERE id = ?;");
        statement.setInt(1, Integer.parseInt(id));
        statement.executeUpdate();
    }

    public void deleteEducation(Education education) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM educations WHERE id = ?;");
        statement.setInt(1, education.getId());
        statement.executeUpdate();
    }

    public void deleteEducations() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM educations;");
        statement.executeUpdate();
    }
    
}
