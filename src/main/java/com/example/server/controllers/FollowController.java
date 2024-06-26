package main.java.com.example.server.controllers;

import main.java.com.example.server.dataAccesses.FollowDAO;
import main.java.com.example.server.models.Follow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class FollowController {

    private final FollowDAO followDAO;
    private final ObjectMapper objectMapper;

    public FollowController() throws SQLException {
        this.followDAO = new FollowDAO(); 
        this.objectMapper = new ObjectMapper();
    }

    public void createFollow(int id, int followerId, int followingId, boolean isConnection) throws SQLException {
        Follow follow = new Follow(id, followerId, followingId, isConnection);
        followDAO.saveFollow(follow);
    }

    public void deleteFollow(int id) throws SQLException {
        followDAO.deleteFollow(id);
    }

    public void deleteFollow(Follow follow) throws SQLException {
        followDAO.deleteFollow(follow);
    }

    public void deleteFollows() throws SQLException {
        followDAO.deleteFollows();
    }

    public String getFollow(int id) throws SQLException, JsonProcessingException {
        Follow follow = followDAO.getFollow(id);
        return objectMapper.writeValueAsString(follow);
    }

    public String getFollows() throws SQLException, JsonProcessingException {
        ArrayList<Follow> follows = followDAO.getFollows();
        return objectMapper.writeValueAsString(follows);
    }

    public String  getFollowers (int followongId) throws SQLException , JsonProcessingException{
        ArrayList <Follow> followers = followDAO.getFollowers(followongId);
        return objectMapper.writeValueAsString(followers);
    }

    public String getFollowings (int followerId)throws SQLException, JsonProcessingException{
        ArrayList <Follow> followings = followDAO.getFollowings(followerId);
        return objectMapper.writeValueAsString(followings);
    }
}
