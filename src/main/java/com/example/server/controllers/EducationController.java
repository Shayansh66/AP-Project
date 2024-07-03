package main.java.com.example.server.controllers;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.example.server.dataAccesses.EducationDAO;
import main.java.com.example.server.dataAccesses.UserDAO;
import main.java.com.example.server.models.Education;
public class EducationController {
    private final EducationDAO educationDAO;
    private final UserDAO UserDAO;
    private final ObjectMapper objectMapper;

    public EducationController () throws SQLException {
        educationDAO = new EducationDAO();
        objectMapper = new ObjectMapper();
        UserDAO = new UserDAO();

    }

    public void  createEducation (int id, int userid, String institutionName, String studyField, Timestamp startDate, Timestamp endDate,
    float grade, String descriptionOfActivities, String description, boolean notifyChanges) throws SQLException {
    Education education = new Education(id, userid, institutionName, studyField, startDate, endDate, grade, descriptionOfActivities, description, notifyChanges);
    educationDAO.saveEducation(education);

    }   

    public void updateEducation (int id, int userid, String institutionName, String studyField, Timestamp startDate, Timestamp endDate,
    float grade, String descriptionOfActivities, String description, boolean notifyChanges) throws SQLException {
        Education education = new Education(id, userid, institutionName, studyField, startDate, endDate, grade, descriptionOfActivities, description, notifyChanges);
        educationDAO.updateEducation(education);
    }

    public void deleteEducation (int id ) throws SQLException {
        educationDAO.deleteEducation(id);
    }

    public void deleteEducation (Education education ) throws SQLException {
        educationDAO.deleteEducation(education);
    }

    public  void deleteEducations () throws SQLException {
        educationDAO.deleteEducations();
    }

    public  String getEducationById (int id ) throws SQLException , JsonProcessingException {
        Education education = educationDAO.getEducationById(id);
        return objectMapper.writeValueAsString(education);
    }

    public String getEducationByUserId (int userid ) throws SQLException , JsonProcessingException {
        Education userEducation = educationDAO.getEducationsByUserId(userid);
        return objectMapper.writeValueAsString(userEducation);
    }



}


