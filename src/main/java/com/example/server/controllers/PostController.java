package main.java.com.example.server.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.example.server.dataAccesses.PostDAO;
import main.java.com.example.server.models.Comment;
import main.java.com.example.server.models.Post;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;



public class PostController {
    private final PostDAO postDAO;

    public PostController() throws SQLException {
        postDAO = new PostDAO();
        
    }

    public  void createPost (int id , int writterid , String content , int likeNumbers, 
    int commentNumbers , Timestamp createDate , int relatedGroupId ) throws SQLException {
       Post post = new Post(id, writterid, content, likeNumbers, commentNumbers, createDate, relatedGroupId) ;
       postDAO.savePost(post);     
    }
    
    public void updatePost (int id , int writterid , String content , int likeNumbers, 
    int commentNumbers , Timestamp createDate , int relatedGroupId ) throws SQLException {
        Post post = new Post(id, writterid, content, likeNumbers, commentNumbers, createDate, relatedGroupId);
        postDAO.updatePost(post);
     
    }

    public void deletePosts () throws SQLException {
        postDAO.deletePosts();
    }

    public void deletepost (int id) throws SQLException {
        postDAO.deletePost(id);
    }
    public void deletepost (Post post) throws SQLException {
        postDAO.deletePost(post);

    }

    public String getPosts ()  throws SQLException, JsonProcessingException  {
        ArrayList <Post> posts = postDAO.getPosts();
        ObjectMapper ob = new ObjectMapper();
        return ob.writeValueAsString(posts);
    }

<<<<<<< HEAD
    public String getPostsbyWriterId (int userId)  throws SQLException, JsonProcessingException {
        ArrayList <Post> posts = postDAO.getPosts(getPostsbyWriterId(userId));
=======
    public String getPostsbyWriterId (int userId) throws SQLException, JsonProcessingException {
        ArrayList<Post> posts = postDAO.getPosts(userId);
>>>>>>> 4cc11c3f58262ede55fb7a150eb8a42326cb286e
        ObjectMapper ob = new ObjectMapper();
         return ob.writeValueAsString(posts);
    }


    public String getPost (int id ) throws SQLException, JsonProcessingException {
        Post post = postDAO.getPost(id);
        ObjectMapper ob = new ObjectMapper();
        return ob.writeValueAsString(post);
    }


    public String getUserComments (int userid) throws SQLException, JsonProcessingException  {
        ArrayList <Post> userComments = postDAO.getComments(userid);
        ObjectMapper ob =new ObjectMapper();
        return ob.writeValueAsString(userComments);

    }

    public String getPostComments (int postId) throws SQLException, JsonProcessingException  {
        ArrayList <Comment > postComments = postDAO.getCommentsByPostID(postId);
        ObjectMapper ob = new ObjectMapper(); 
        return ob.writeValueAsString(postComments);


    }
}


