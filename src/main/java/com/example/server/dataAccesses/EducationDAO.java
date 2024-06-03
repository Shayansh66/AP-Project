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
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS educations (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOT NULL, institutionname VARCHAR(40), studyfield VARCHAR(40), startdate TIMESTAMP, enddate TIMESTAMP, grade DECIMAL(2, 2), descriptionofactivities VARCHAR(500), description VARCHAR(1000), notifychanges BOOLEAN);");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE educations AUTO_INCREMENT = 43000000;");
        statement.executeUpdate();
    }

    public void saveEducation(Education education) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO educations (userid, institutionname, studyfield, startdate, enddate, grade, descriptionofactivities, description notifychanges) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        statement.setInt(1, education.getUserId());
        statement.setString(2, education.getInstitutionName());
        statement.setString(3, education.getStudyField());
        statement.setTimestamp(4, education.getStartDate());
        statement.setTimestamp(5, education.getEndDate());
        statement.setDouble(6, education.getGrade());
        statement.setString(7, education.getDescriptionOfActivities());
        statement.setString(8, education.getDescription());
        statement.setBoolean(9, education.isNotifyChanges());
        statement.executeUpdate();
    }

    public void updateEducation(Education education) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE educations SET userid = ?, institutionname = ?, studyfield = ?, startdate = ?, enddate = ?, grade = ?, descriptionofactivities = ?, description = ?, notifychanges = ? WHERE id = ?;");
        statement.setInt(1, education.getUserId());
        statement.setString(2, education.getInstitutionName());
        statement.setString(3, education.getStudyField());
        statement.setTimestamp(4, education.getStartDate());
        statement.setTimestamp(5, education.getEndDate());
        statement.setDouble(6, education.getGrade());
        statement.setString(7, education.getDescriptionOfActivities());
        statement.setString(8, education.getDescription());
        statement.setBoolean(9, education.isNotifyChanges());
        statement.setInt(10, education.getId());
        statement.executeUpdate();
    }

    public void deleteEducation(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM educations WHERE id = ?;");
        statement.setInt(1, id);
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

    public Education getEducationById(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM educations WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Education education = new Education();
            education.setId(resultSet.getInt("id"));
            education.setUserId(resultSet.getInt("userid"));
            education.setInstitutionName(resultSet.getString("institutionname"));
            education.setStudyField(resultSet.getString("studyfield"));
            education.setStartDate(resultSet.getTimestamp("startdate"));
            education.setEndDate(resultSet.getTimestamp("enddate"));
            education.setGrade(resultSet.getFloat("grade"));
            education.setDescription(resultSet.getString("description"));
            education.setNotifyChanges(resultSet.getBoolean("notifychanges"));
            return education;
        }

        return null;
    }

    public Education getEducationByUserIs(int userid) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM educations WHERE userid = ?;");
        statement.setInt(1, userid);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Education education = new Education();
            education.setId(resultSet.getInt("id"));
            education.setUserId(resultSet.getInt("userid"));
            education.setInstitutionName(resultSet.getString("institutionname"));
            education.setStudyField(resultSet.getString("studyfield"));
            education.setStartDate(resultSet.getTimestamp("startdate"));
            education.setEndDate(resultSet.getTimestamp("enddate"));
            education.setGrade(resultSet.getFloat("grade"));
            education.setDescription(resultSet.getString("description"));
            education.setNotifyChanges(resultSet.getBoolean("notifychanges"));
            return education;
        }

        return null;
    }
    
    public Education getEducations() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM educations;");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Education education = new Education();
            education.setId(resultSet.getInt("id"));
            education.setUserId(resultSet.getInt("userid"));
            education.setInstitutionName(resultSet.getString("institutionname"));
            education.setStudyField(resultSet.getString("studyfield"));
            education.setStartDate(resultSet.getTimestamp("startdate"));
            education.setEndDate(resultSet.getTimestamp("enddate"));
            education.setGrade(resultSet.getFloat("grade"));
            education.setDescription(resultSet.getString("description"));
            education.setNotifyChanges(resultSet.getBoolean("notifychanges"));
            return education;
        }

        return null;
    }
    
}
