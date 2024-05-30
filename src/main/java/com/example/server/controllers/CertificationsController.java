package main.java.com.example.server.controllers;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.time.LocalDate;

import main.java.com.example.server.dataAccesses.CertificationDAO;
import main.java.com.example.server.models.Certifications;

public class CertificationsController {
    private final CertificationDAO certificationDAO;

    public CertificationsController () throws SQLException {
        certificationDAO = new CertificationDAO();
    }

    public void createCertification (int id  , int userId , String name ,  String institution , Timestamp issueDate , 
    Timestamp expireDate , String creditId , String  websiteRefrence   ) throws SQLException {

        Certifications certification = new Certifications(id, userId, name, institution, issueDate, expireDate, creditId, websiteRefrence);
        certificationDAO.saveCertificate(certification);
    }


    public void updateCertification (int id  , int userId , String name ,  String institution , Timestamp issueDate , 
    Timestamp expireDate , String creditId , String  websiteRefrence ) throws SQLException {
        Certifications certification = new Certifications(id, userId, name, institution, issueDate, expireDate, creditId, websiteRefrence);
        certificationDAO.updateCertificate(certification);
        
    }
     
    public void deleteCertification (int id) throws SQLException {
        certificationDAO.delteCertification(id);
    }

}
