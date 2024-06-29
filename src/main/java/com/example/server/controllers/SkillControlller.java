package main.java.com.example.server.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.example.server.dataAccesses.SkillDAO;
import main.java.com.example.server.models.Skill;

public class SkillControlller {
    private final SkillDAO skillDAO;
    private final ObjectMapper objectMapper; 
    private final UserController usercController;

    public SkillControlller() throws SQLException {
        skillDAO = new SkillDAO();
        objectMapper = new ObjectMapper();
        usercController = new UserController();
    }
    public String createSkill (int id, String explaination, int userid) throws SQLException {
        int skillnumbers  = skillDAO.getSkillsByuserid(userid).size();
        if (!usercController.isUserAlreadyExist(userid)) {
            return "user not found";
        }
        if (skillnumbers >= 5) {
            return "you can only have 5 skills";
        }
        else {
        Skill skill = new Skill(id, explaination, userid);
        return "sucsessful";
    }
}
    public String updateSkill (int id, String explaination, int userid) throws SQLException {
        if (!usercController.isUserAlreadyExist(userid)) {
            return "user not found";
        }
        skillDAO.updateSkill(id, explaination, userid);
        return "sucsessful";
    }

    public String getSkill (int id ,  int userid ) throws SQLException , JsonProcessingException{
        if (!usercController.isUserAlreadyExist(userid)) {
            return "user not found";
        }
        
        String skill = skillDAO.getSkill(id);
        return objectMapper.writeValueAsString(skill);
    }


    public String getSkillsByUserId (int userId) throws SQLException , JsonProcessingException {
       
        if (!usercController.isUserAlreadyExist(userId)) {
            return "user not found";
        }
         ArrayList <String> UserSkills = skillDAO.getSkillsByuserid(userId);
        return objectMapper.writeValueAsString(UserSkills);
    }
    public void delteSkill (int id) throws SQLException {
        skillDAO.deleteSkill(id);
    }



}
