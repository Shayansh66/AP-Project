package main.java.com.example.server.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.example.server.dataAccesses.LikeDAO;
import main.java.com.example.server.models.Like;

public class LikeController {
    private final LikeDAO likeDAO;
    private final ObjectMapper objectMapper;

    public LikeController  ()  throws SQLException
    {
        likeDAO = new LikeDAO();
        objectMapper = new ObjectMapper();
    }

    public String  createLike (int id, int likerid, int likedPostid) throws SQLException {
        Like like = new Like(id, likerid, likedPostid);
        if (likeDAO.isLiking(likerid, likedPostid)) {
            likeDAO.deleteLike(like);
            return "like  toggled sucsessfully";
        }
        else {
            likeDAO.saveLike(like);
            return "like created sucsessfully";
        }
    }

    public void deleteLikes () throws SQLException {
        likeDAO.deleteLikes();
    }


    public String getlikes () throws SQLException,JsonProcessingException {
        ArrayList <Like> likes = likeDAO.getLikes();
        return objectMapper.writeValueAsString(likes);
    }
 
    public String getlikesByUserId (int userid) throws SQLException,JsonProcessingException {
        ArrayList <Like> likes = likeDAO.getLikesByUserid(userid);
        return objectMapper.writeValueAsString(likes);
    }
    public String getlikesByPostId (int postid) throws SQLException,JsonProcessingException {
        ArrayList <Like> likes = likeDAO.getLikesByPostid(postid);
        return objectMapper.writeValueAsString(likes);
    }

    public boolean isLiking(int likerId, int likedPostId) throws SQLException {
        return likeDAO.isLiking(likerId, likedPostId);
    }
}
