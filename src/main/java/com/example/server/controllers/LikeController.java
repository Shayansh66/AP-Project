package main.java.com.example.server.controllers;

import java.sql.SQLException;

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

    public void  createLike (int id, int likerid, int likedPostid) throws SQLException {
        Like like = new Like(id, likerid, likedPostid);
        if (likeDAO.isLikeing(likerid, likedPostid)) {
            likeDAO.deleteLike(like);
        }
        else {
            likeDAO.saveLike(like);
        }
    }

    public void deleteLikes () throws SQLException {
        likeDAO.deleteLikes();
    }
}
