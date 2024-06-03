package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Certification;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class CertificationDAO {
    
    private Connection theConnection;
    
    public CertificationDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS certifications (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOT NULL, name VARCHAR(40), institutionname VARCHAR(40), issuedate TIMESTAMP, expiredate TIMESTAMP, creditid VARCHAR(40), refrencewebsite VARCHAR(255));");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TALBE certifications AUTO_INCREMENT = 45000000;");
        statement.executeUpdate();
    }

    public void saveCertificate(Certification certification) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO certifications (userid, name, institution, issuedate, expiredate, creditid, refrencewebsite) VALUES (?, ?, ?, ?, ?, ?, ?);");
        statement.setInt(1, certification.getUserId());
        statement.setString(2, certification.getName());
        statement.setString(3, certification.getInstitution());
        statement.setTimestamp(4, certification.getIssueDate());
        statement.setTimestamp(5, certification.getExpireDate());
        statement.setString(6, certification.getCreditId());
        statement.setString(7, certification.getRefrenceWebsite());
        statement.executeUpdate();
    }
    
    public void updateCertificate(Certification certification) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE certifications SET userid = ?, name = ?, institution = ?, issuedate = ?, expiredate = ?, creditid = ?, refrencewebsite = ? WHERE id = ?;");
        statement.setInt(1, certification.getUserId());
        statement.setString(2, certification.getName());
        statement.setString(3, certification.getInstitution());
        statement.setTimestamp(4, certification.getIssueDate());
        statement.setTimestamp(5, certification.getExpireDate());
        statement.setString(6, certification.getCreditId());
        statement.setString(7, certification.getRefrenceWebsite());
        statement.setInt(8, certification.getId());
        statement.executeUpdate();
    }

    public void deleteCertification(Certification certification) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM certifications WHERE id = ?;");
        statement.setInt(1, certification.getId());
        statement.executeUpdate();
    }

    public void deleteCertification(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM certifications WHERE id = ?;");
        statement.setInt(1,(id));
        statement.executeUpdate();
    }

    public void deleteCertifications() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM certifications;");
        statement.executeUpdate();
    }

    public  Certification getCertification(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM certifications WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Certification certification = new Certification();
            certification.setId(resultSet.getInt("id"));
            certification.setUserId(resultSet.getInt("userid"));
            certification.setName(resultSet.getString("name"));
            certification.setInstitution(resultSet.getString("institution"));
            certification.setIssueDate(resultSet.getTimestamp("issuedate"));
            certification.setExpireDate(resultSet.getTimestamp("expiredate"));
            certification.setCreditId(resultSet.getString("creditid"));
            certification.setRefrenceWebsite(resultSet.getString("refrencewebsite"));
            return certification;
        }

        return null;
    }

    public ArrayList<Certification> getCertifications() throws SQLException {
        ArrayList<Certification> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM certifications");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Certification certification = new Certification();
            certification.setId(resultSet.getInt("id"));
            certification.setUserId(resultSet.getInt("userid"));
            certification.setName(resultSet.getString("name"));
            certification.setInstitution(resultSet.getString("institution"));
            certification.setIssueDate(resultSet.getTimestamp("issuedate"));
            certification.setExpireDate(resultSet.getTimestamp("expiredate"));
            certification.setCreditId(resultSet.getString("creditid"));
            certification.setRefrenceWebsite(resultSet.getString("refrencewebsite"));
            list.add(certification);
        }

        return list;
    }
    
    public ArrayList<Certification> getCertificationsByUserID(int userid) throws SQLException {
        ArrayList<Certification> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM certifications WHERE userid = ?");
        statement.setInt(1, userid);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Certification certification = new Certification();
            certification.setId(resultSet.getInt("id"));
            certification.setUserId(resultSet.getInt("userid"));
            certification.setName(resultSet.getString("name"));
            certification.setInstitution(resultSet.getString("institution"));
            certification.setIssueDate(resultSet.getTimestamp("issuedate"));
            certification.setExpireDate(resultSet.getTimestamp("expiredate"));
            certification.setCreditId(resultSet.getString("creditid"));
            certification.setRefrenceWebsite(resultSet.getString("refrencewebsite"));
            list.add(certification);
        }

        return list;
    }



    
}
