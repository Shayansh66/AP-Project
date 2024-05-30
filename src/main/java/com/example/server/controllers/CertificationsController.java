package main.java.com.example.server.controllers;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        certificationDAO.deleteCertification(id);
    }

    public void deleteCertification (Certifications certification) throws SQLException {
        certificationDAO.deleteCertification (certification);
    }

    public void deleteCertifications () throws SQLException {
        certificationDAO.deleteCertifications();
    }

    public String getCertification(int id) throws SQLException, JsonProcessingException {
        Certifications certification = certificationDAO.getCertification(id);
        ObjectMapper ob = new ObjectMapper();
        return ob.writeValueAsString(certification);
    }

    public String getCertificationsByUserId (int userId) throws SQLException, JsonProcessingException {
        ArrayList <Certifications> userCertifications =  certificationDAO.getCertificationsByUserID(userId);
        ObjectMapper ob = new ObjectMapper() ;

        return ob.writeValueAsString(userCertifications);

    }

    public String getCertifications () throws SQLException, JsonProcessingException {
        ArrayList <Certifications> certifications = certificationDAO.getCertifications();
        ObjectMapper ob = new ObjectMapper();

        return ob.writeValueAsString(certifications);
    }



}
