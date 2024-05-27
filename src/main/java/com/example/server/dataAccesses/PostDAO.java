package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Comment;
import main.java.com.example.server.models.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class PostDAO {

    private Connection theConnection;

    public PostDAO() throws SQLException {
        this.theConnection = DatabaseConnectionManager.getTheConnection();
        createPostTable();
    }

    public void createPostTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS posts (id INT PRIMARY KEY AUTO_INCREMENT, writterid INT NOT NULL, content VARCHAR(3000), likenumber INT DEFAULT 0, commentnumber INT DEFAULT 0, relatedpostid INT DEFAULT -1, createdate TIMESTAMP DEFAULT NOW(), CONSTRAINT fk_writterid FOREIGN KEY (writterid) REFERENCES users(id), CONSTRAINT fk_relatedpost FOREIGN KEY (relatedpostid) REFERENCES posts(id));");
        statement.executeUpdate();
    }


    public void savePost(Post post) throws SQLException {
        PreparedStatement statement = null;
        try {
            if (post instanceof Comment comment) {
                statement = theConnection.prepareStatement("INSERT INTO posts (writterid, content, relatedpostid) VALUES (?, ?, ?);");
                statement.setInt(3, comment.getRelatedPostid());
            }
            else { // post is instance of Post
                statement = theConnection.prepareStatement("INSERT INTO posts (writterid, content) VALUES (?, ?);");
            }
            statement.setInt(1, post.getWritterid());
            statement.setString(2, post.getContent());
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void updatePost(Post post) throws SQLException {
        PreparedStatement statement = null;
        try {
            if (post instanceof Comment comment) {
                statement = theConnection.prepareStatement("UPTADE posts SET writterid = ?, content = ?, likenumber = ?, commentnumber = ?, relatedpostid = ?;");
                statement.setInt(5, comment.getRelatedPostid());
            }
            else {
                statement = theConnection.prepareStatement("UPTADE posts SET writterid = ?, content = ?, likenumber = ?, commentnumber = ?;");
            }
            statement.setInt(1, post.getWritterid());
            statement.setString(2, post.getContent());
            statement.setInt(3, post.getLiKeNumber());
            statement.setInt(4, post.getCommentNumber());
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void deletePost(String id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM posts WHERE id = ?;");
        statement.setInt(1, Integer.parseInt(id));
        statement.executeUpdate();
    }

    public void deletePost(Post post) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM posts WHERE id = ?;");
        statement.setInt(1, post.getId());
        statement.executeUpdate();
    }

    public void deletePosts() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM posts;");
        statement.executeUpdate();
    }

    public Post getPost(String id) throws SQLException {
        var ID = Integer.parseInt(id);
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM posts WHERE id = ?;");
        statement.setInt(1, ID);
        ResultSet resultset = statement.executeQuery();
        if (resultset.next()) {
            var writterid = resultset.getInt("writterid");
            var content = resultset.getString("content");
            var likenumber = resultset.getInt("likenumber");
            var commentnumber = resultset.getInt("commentnumber");
            var timestamp = resultset.getTimestamp("createdate");
            var relatedPostid = resultset.getInt("relatedpostid");

            if (relatedPostid != -1) {    // it is a Comment
                return new Comment(ID, writterid, content, likenumber, commentnumber, timestamp, relatedPostid);
            }
            else {  // it is a Post
                return new Post(ID, writterid, content, likenumber, commentnumber, timestamp);
            }
        }
        return null;
    }
    
}
