package main.java.com.example.server.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.example.server.dataAccesses.HashtagDAO;
import main.java.com.example.server.dataAccesses.PostDAO;

public class HashtagController {
    private final HashtagDAO hashtagDAO ;
    private final  ObjectMapper objectMapper ;
    private final PostDAO postdDAO;

    public HashtagController () throws SQLException {
        hashtagDAO = new HashtagDAO();
        objectMapper = new ObjectMapper();
        postdDAO = new PostDAO();
    }

    public String createHashtag (int postid, String content) throws SQLException {
    if (postdDAO.getPost(postid) == null) {
        return "Post not found";
    }
    else {
        hashtagDAO.saveHashtag(postid, content);
        return "sucsessful";
    }
}   

    public String deleteHashtag (String content) throws SQLException {
        hashtagDAO.deleteHashtag(content);
        return "sucsessful";
    }

    public String deleteHashtags (int postId)throws SQLException {
        if (postdDAO.getPost(postId) == null) {
            return "Post not found";
        }
        else {
            hashtagDAO.deleteHashtags(postId);
            return "sucsessful";
        }
    }

    public String getpostHashtags (int postid) throws SQLException , JsonProcessingException {
        if (postdDAO.getPost(postid) == null) {
            return "Post not found";
        }
        else {
            ArrayList <Integer> postHashtags = hashtagDAO.getHashtagsPost(postid);
            return objectMapper.writeValueAsString(postHashtags);
        }
    }

    public String getPosts (String content) throws SQLException , JsonProcessingException {
        ArrayList <Integer> posts = hashtagDAO.getPosts(content);
        return objectMapper.writeValueAsString(posts);
    }
}