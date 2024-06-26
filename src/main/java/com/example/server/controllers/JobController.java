package main.java.com.example.server.controllers;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.example.server.dataAccesses.JobDAO;
import main.java.com.example.server.models.Job;

public class JobController {
    private final JobDAO jobDAO;
    private final ObjectMapper objectMapper;
     
    public JobController () throws SQLException {
        jobDAO = new JobDAO();
        objectMapper = new ObjectMapper();
    }

    public void createJob (int id, int userid, String title, String companyName, String workplace, boolean isworking,
            String workType, String jobType, Timestamp startDate, Timestamp enddate, String description,
            boolean notifyChanges) {
                Job job = new Job(id, userid, title, companyName, workplace, isworking, workType, jobType, startDate, enddate, description, notifyChanges)
                jobDAO.saveJob(job);
    }
    public void updateJob (int id, int userid, String title, String companyName, String workplace, boolean isworking,
    String workType, String jobType, Timestamp startDate, Timestamp enddate, String description,
    boolean notifyChanges) throws SQLException {
        Job job = new Job(id, userid, title, companyName, workplace, isworking, workType, jobType, startDate, enddate, description, notifyChanges);
        jobDAO.updateJob(job);
    }

    public void delteJob (Job job) throws SQLException {
        jobDAO.deleteJob(job);
    }

    public void delteJob (int id) throws SQLException {
        jobDAO.deleteJob(id);
    }

    public void delteJobs () throws SQLException {
        jobDAO.deleteJobs();
    }

    public String getJob (int id) throws SQLException ,JsonProcessingException {
        Job job = jobDAO.getJob(id);
        return objectMapper.writeValueAsString(job);
    }
    public String getJobs () throws SQLException ,JsonProcessingException {
        ArrayList <Job> jobs = jobDAO.getJobs();
        return objectMapper.writeValueAsString(jobs);
    }

    public String getUserJobs (int userId) throws SQLException ,JsonProcessingException {
        ArrayList <Job> userJobs = jobDAO.getUserJobs(userId);
        return objectMapper.writeValueAsString(userJobs);
    }
}
