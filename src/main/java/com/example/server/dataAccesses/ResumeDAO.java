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

    public void saveResume(Resume resume) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO resumes (ownerid, positionid, resumefile, description) VALUES (?, ?, ?, ?);");
        statement.setInt(1, resume.getOwnerId());
        statement.setInt(2, resume.getPositionId());
        statement.setString(3, resume.getResumeFile());
        statement.setString(4, resume.getDescription());
        statement.executeUpdate();
    }
    
    public void updateResume(Resume resume) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE resumes SET ownerid = ?, positionid = ?, resumefile = ?, description = ? WHERE id = ?;");
        statement.setInt(1, resume.getOwnerId());
        statement.setInt(2, resume.getPositionId());
        statement.setString(3, resume.getResumeFile());
        statement.setString(4, resume.getDescription());
        statement.setInt(5, resume.getId());
        statement.executeUpdate();
    }

    public void deleteResume(Resume resume) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM resumes WHERE id = ?;");
        statement.setInt(1, resume.getId());
        statement.executeUpdate();
    }

    public void deleteResume(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM resumes WHERE id = ?;");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void deleteResumes() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM resumes;");
        statement.executeUpdate();
    }

    public Resume getResume(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM resumes WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Resume resume = new Resume();
            resume.setId(resultSet.getInt("id"));
            resume.setOwnerId(resultSet.getInt("ownerid"));
            resume.setPositionId(resultSet.getInt("positionid"));
            resume.setResumeFile(resultSet.getString("resumefile"));
            resume.setDescription(resultSet.getString("description"));
            return resume;
        }

        return null;
    }
    
    public ArrayList<Resume> getResumesByUserId(int userid) throws SQLException {
        ArrayList<Resume> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM resumes WHERE userid = ?;");
        statement.setInt(1, userid);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Resume resume = new Resume();
            resume.setId(resultSet.getInt("id"));
            resume.setOwnerId(resultSet.getInt("ownerid"));
            resume.setPositionId(resultSet.getInt("positionid"));
            resume.setResumeFile(resultSet.getString("resumefile"));
            resume.setDescription(resultSet.getString("description"));
            list.add(resume);
        }

        return list;
    }
    
    public ArrayList<Resume> getResumes() throws SQLException {
        ArrayList<Resume> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM resumes;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Resume resume = new Resume();
            resume.setId(resultSet.getInt("id"));
            resume.setOwnerId(resultSet.getInt("ownerid"));
            resume.setPositionId(resultSet.getInt("positionid"));
            resume.setResumeFile(resultSet.getString("resumefile"));
            resume.setDescription(resultSet.getString("description"));
            list.add(resume);
        }

        return list;
    }

}
