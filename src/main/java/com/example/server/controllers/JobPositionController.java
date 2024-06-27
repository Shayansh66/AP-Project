package main.java.com.example.server.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.text.Position;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.example.server.dataAccesses.JobPositionDAO;
import main.java.com.example.server.models.JobPosition;

public class JobPositionController {

    private final JobPositionDAO jobPositionDAO;
    private final ObjectMapper objectMapper;

    public JobPositionController() throws SQLException {
        jobPositionDAO = new JobPositionDAO();
        objectMapper = new ObjectMapper();
    }
    
    public void createJobPosition (int id, int userid, String title, String companyName, String worktype, String jobType,
    String profession, String description) throws SQLException {
        JobPosition jobPosition = new JobPosition(id, userid, title, companyName, worktype, jobType, profession, description);
        jobPositionDAO.saveJobPosition(jobPosition);
    }

    public void updateJobPosition (int id, int userid, String title, String companyName, String worktype, String jobType,
    String profession, String description) throws SQLException {
        // Fetch the JobPosition by ID
        JobPosition existingJobPosition = jobPositionDAO.getJobPosition(id);

        if (existingJobPosition != null) {
            // Update the JobPosition
            JobPosition updatedJobPosition = new JobPosition(id, userid, title, companyName, worktype, jobType, profession, description);
            jobPositionDAO.updateJobPosition(updatedJobPosition);
        } else {
            System.out.println("JobPosition with ID " + id + " does not exist.");

        }
    }

    public void deleteJobPosition (int id) throws SQLException {
        jobPositionDAO.deleteJobPosition(id);
    }

    public void deleteJobPosition (JobPosition position) throws SQLException {
        jobPositionDAO.deleteJobPosition(position);
    }

    public void deleteJObPositions () throws SQLException {
        jobPositionDAO.deleteJObPositions();
    }

    public String  getJobPosition (int id ) throws SQLException, JsonProcessingException {
        JobPosition jobPosition = jobPositionDAO.getJobPosition(id);
        return objectMapper.writeValueAsString(jobPosition);
    }
    public String  getJobPositionbyUserId (int userId ) throws SQLException, JsonProcessingException {
        ArrayList <JobPosition> userjobPosition = jobPositionDAO.getJobPositionsByUserId(userId);
        return objectMapper.writeValueAsString(userjobPosition);
    }

    public String getJobPositions () throws SQLException , JsonProcessingException {
        ArrayList <JobPosition> jobPositions = jobPositionDAO.getJobPositions();
        return objectMapper.writeValueAsString(jobPositions);
    }
}
