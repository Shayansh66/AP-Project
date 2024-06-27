package main.java.com.example.server.controllers;

import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.example.server.dataAccesses.SkillDAO;
import main.java.com.example.server.models.Skill;

public class SkillControlller {
    private final SkillDAO skillDAO;
    private final ObjectMapper objectMapper;

    public SkillControlller() throws SQLException {
        skillDAO = new SkillDAO();
        objectMapper = new ObjectMapper();
    }

    public void createSkill (int id, String explaination , String jobType) throws SQLException {
    
        skillDAO.saveSkill(explaination, id,jobType);
    }
    public updateJobPosition (int id, String explaination , String jobType) throws SQLException {
        skillDAO.updateSkill(explaination, id, jobType);
    }
}
