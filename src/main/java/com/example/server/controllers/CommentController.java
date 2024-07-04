package main.java.com.example.server.controllers;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.example.server.dataAccesses.PostDAO;
import main.java.com.example.server.models.Comment;

public class CommentController {
    private final PostDAO postDAO ;
    ObjectMapper objectMapper ;
    public CommentController () throws SQLException {
        postDAO = new PostDAO();
        objectMapper = new ObjectMapper();
    }


    public String  createComment (int id, int writterid, String content, int likeNumber, int commentNumber, Timestamp createDate,int relatedPostId) throws SQLException {
        if (postDAO.getPost(relatedPostId ) == null ) {
            return "no post found with this id ";
        }
        else {
        Comment comment = new Comment(id, writterid, content, likeNumber, commentNumber, createDate, relatedPostId);
        postDAO.savePost(comment);
        return "sucssesful";
    }
}
public String  updateComment (int id, int writterid, String content, int likeNumber, int commentNumber, Timestamp createDate,int relatedPostId) throws SQLException {
    if (postDAO.getPost(relatedPostId ) == null ) {
        return "no post found with this id ";
    }
    else if (postDAO.getPost(id) == null) {
        return "no comment found with this id";
    }
    else {
    Comment comment = new Comment(id, writterid, content, likeNumber, commentNumber, createDate, relatedPostId);
    postDAO.updatePost(comment);(comment);
    return "sucssesful";
}
}
public String getPostComments (int relatedPostId) throws SQLException , JsonProcessingException {
    ArrayList <Comment> postComments = postDAO.getCommentsByPostID(relatedPostId);
    return objectMapper.writeValueAsString(postComments);
}

public String getUserComments (int userid ) throws SQLException , JsonProcessingException {
    ArrayList <Comment> userComments = postDAO.getComments(userid);
    return objectMapper.writeValueAsString(userComments);
}


}
