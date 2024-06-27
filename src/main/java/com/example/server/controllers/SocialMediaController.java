package main.java.com.example.server.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.example.server.dataAccesses.SocialMediaDAO;
import main.java.com.example.server.models.SocialMedia;

public class SocialMediaController {
    private final SocialMediaDAO socialMediaDAO;
    private final ObjectMapper objectMapper ;

    public SocialMediaController() throws SQLException {
        socialMediaDAO = new SocialMediaDAO();
        objectMapper = new ObjectMapper();
    }

    public void createSocialMedia (int id, int userid, String application, String contactid) throws SQLException {
        SocialMedia socialMedia = new SocialMedia(id, userid, application, contactid);
        socialMediaDAO.saveSocialMedia(socialMedia);
    }

    public void updateSocialMedia (int id, int userid, String application, String contactid) throws SQLException {
        SocialMedia socialMedia = new SocialMedia(id, userid, application, contactid);
        socialMediaDAO.saveSocialMedia(socialMedia);
    }

    public void deleteSocialMedia (SocialMedia socialMedia) throws SQLException {
        socialMediaDAO.deleteSocialMedia(socialMedia);
    }

    public void deleteSocialMedia (int id) throws SQLException {
        socialMediaDAO.deleteSocialMedia(id);
    }

    public void deleteSocialMedias () throws SQLException {
        socialMediaDAO.deleteSocialMedias();
    }

    public String  getSocailmedia (int id )throws SQLException , JsonProcessingException {
        SocialMedia socialMedia = socialMediaDAO.getSocialMedia(id);
        return objectMapper.writeValueAsString(socialMedia);
    }
    public String getSocialMediaByUserId (int userId) throws SQLException , JsonProcessingException {
        ArrayList <SocialMedia> userSocialMedia = socialMediaDAO.getSocialMediasByUserId(userId);
        return objectMapper.writeValueAsString(userSocialMedia);
    }
    public String getSocialMedias () throws SQLException ,JsonProcessingException {
        ArrayList <SocialMedia> socialMedias = socialMediaDAO.getSocialMedias();
        return objectMapper.writeValueAsString(socialMedias);
    }
}
