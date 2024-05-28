package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Certifications;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class CertificationDAO {
    
    private Connection theConnection;
    
    public CertificationDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS certifications (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOT NULL, name VARCHAR(40), instutationname VARCHAR(40), issuedate TIMESTAMP, expiredate TIMESTAMP, creditid VARCHAR(40), refrencewebsite VARCHAR(255));");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TALBE certifications AUTO_INCREMENT = 44000000;");
        statement.executeUpdate();
    }

    public void saveCertificate(Certifications certification) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO certifications (userid, name, instutation, issuedate, expiredate, creditid, refrencewebsite) VALUES (?, ?, ?, ?, ?, ?, ?);");
        statement.setInt(1, certification.getUserId());
        statement.setString(2, certification.getName());
        statement.setString(3, certification.getInstitution());
        statement.setTimestamp(4, certification.getIssueDate());
        statement.setTimestamp(5, certification.getExpireDate());
        statement.setString(6, certification.getCreditId());
        statement.setString(7, certification.getRefrenceWebsite());
        statement.executeUpdate();
    }
    
    public void updateCertificate(Certifications certification) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE certifications SET userid = ?, name = ?, instutation = ?, issuedate = ?, expiredate = ?, creditid = ?, refrencewebsite = ?;");
        statement.setInt(1, certification.getUserId());
        statement.setString(2, certification.getName());
        statement.setString(3, certification.getInstitution());
        statement.setTimestamp(4, certification.getIssueDate());
        statement.setTimestamp(5, certification.getExpireDate());
        statement.setString(6, certification.getCreditId());
        statement.setString(7, certification.getRefrenceWebsite());
        statement.executeUpdate();
    }

    public void delteCertification(Certifications certification) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM certifications WHERE id = ?;");
        statement.setInt(1, certification.getId());
        statement.executeUpdate();
    }

    public void delteCertification(String id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM certifications WHERE id = ?;");
        statement.setInt(1, Integer.parseInt(id));
        statement.executeUpdate();
    }

    public void delteCertifications() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM certifications;");
        statement.executeUpdate();
    }

    
    
}
