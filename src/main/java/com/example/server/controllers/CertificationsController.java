package main.java.com.example.server.controllers;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.example.server.dataAccesses.CertificationDAO;
import main.java.com.example.server.models.Certification;

public class CertificationsController {
    private final CertificationDAO certificationDAO;

    public CertificationsController () throws SQLException {
        certificationDAO = new CertificationDAO();
    }

    public void createCertification (int id  , int userId , String name ,  String institution , Timestamp issueDate , 
    Timestamp expireDate , String creditId , String  websiteRefrence   ) throws SQLException {

        Certification certification = new Certification(id, userId, name, institution, issueDate, expireDate, creditId, websiteRefrence);
        certificationDAO.saveCertificate(certification);
    }


    public void updateCertification (int id  , int userId , String name ,  String institution , Timestamp issueDate , 
    Timestamp expireDate , String creditId , String  websiteRefrence ) throws SQLException {
        Certification certification = new Certification(id, userId, name, institution, issueDate, expireDate, creditId, websiteRefrence);
        certificationDAO.updateCertificate(certification);
        
    }
     
    public void deleteCertification (int id) throws SQLException {
        certificationDAO.deleteCertification(id);
    }

    public void deleteCertification (Certification certification) throws SQLException {
        certificationDAO.deleteCertification (certification);
    }

    public void deleteCertifications () throws SQLException {
        certificationDAO.deleteCertifications();
    }

    public String getCertification(int id) throws SQLException, JsonProcessingException {
        Certification certification = certificationDAO.getCertification(id);
        ObjectMapper ob = new ObjectMapper();
        return ob.writeValueAsString(certification);
    }

    public String getCertificationsByUserId (int userId) throws SQLException, JsonProcessingException {
        ArrayList <Certification> userCertifications =  certificationDAO.getCertificationsByUserID(userId);
        ObjectMapper ob = new ObjectMapper() ;

        return ob.writeValueAsString(userCertifications);

    }

    public String getCertifications () throws SQLException, JsonProcessingException {
        ArrayList <Certification> certifications = certificationDAO.getCertifications();
        ObjectMapper ob = new ObjectMapper();

        return ob.writeValueAsString(certifications);
    }



}
