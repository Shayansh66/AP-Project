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
    public  void updateJobPosition(String explaination, int objectid, String objecttype, int id) throws SQLException {
        skillDAO.updateSkill(explaination, objectid, objecttype, id);
        
    }
}
