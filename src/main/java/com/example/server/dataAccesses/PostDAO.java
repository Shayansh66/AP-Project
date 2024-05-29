package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Comment;
import main.java.com.example.server.models.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class PostDAO {

    private Connection theConnection;

    public PostDAO() throws SQLException {
        this.theConnection = DatabaseConnectionManager.getTheConnection();
        createPostTable();
    }

    public void createPostTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS posts (id INT PRIMARY KEY AUTO_INCREMENT, writterid INT NOT NULL, content VARCHAR(3000), likenumber INT DEFAULT 0, commentnumber INT DEFAULT 0, createdate TIMESTAMP DEFAULT NOW(), relatedgroupid INT DEFAULT NULL, relatedpostid INT DEFAULT -1, CONSTRAINT fk_writterid FOREIGN KEY (writterid) REFERENCES users(id), CONSTRAINT fk_relatedpost FOREIGN KEY (relatedpostid) REFERENCES posts(id));");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE posts AUTO_INCREMENT = 60000000;");
        statement.executeUpdate();
    }


    public void savePost(Post post) throws SQLException {
        PreparedStatement statement = null;

        try {
            if (post instanceof Comment comment) {
                statement = theConnection.prepareStatement("INSERT INTO posts (writterid, content, relatedgroupid, relatedpostid) VALUES (?, ?, ?, ?);");
                statement.setInt(4, comment.getRelatedPostid());
            }
            else { // post is instance of Post
                statement = theConnection.prepareStatement("INSERT INTO posts (writterid, content) VALUES (?, ?);");
            }
            statement.setInt(1, post.getWritterid());
            statement.setString(2, post.getContent());
            statement.setInt(3, post.getRelatedGroupId());
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
                statement = theConnection.prepareStatement("UPTADE posts SET writterid = ?, content = ?, likenumber = ?, commentnumber = ?, relatedgroupid = ?, relatedpostid = ? WHERE postid = ?;");
                statement.setInt(6, comment.getRelatedPostid());
                statement.setInt(7, comment.getId());
            }

            else {
                statement = theConnection.prepareStatement("UPTADE posts SET writterid = ?, content = ?, likenumber = ?, commentnumber = ?, relatedgroupid = ?, WHERE postid = ?;");
                statement.setInt(6, post.getId());
            }
            statement.setInt(1, post.getWritterid());
            statement.setString(2, post.getContent());
            statement.setInt(3, post.getLiKeNumber());
            statement.setInt(4, post.getCommentNumber());
            statement.setInt(5, post.getRelatedGroupId());
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
            var createdate = resultset.getTimestamp("createdate");
            var relatedGroupId = resultset.getInt("relatedgroupid");
            var relatedPostid = resultset.getInt("relatedpostid");

            if (relatedPostid != -1) {    // it is a Comment
                return new Comment(ID, writterid, content, likenumber, commentnumber, createdate, relatedGroupId, relatedPostid);
            }
            else {  // it is a Post
                return new Post(ID, writterid, content, likenumber, commentnumber, createdate, relatedGroupId);
            }
        }
        return null;

    }

    public ArrayList<Post> getPosts() throws SQLException {
        ArrayList<Post> list = new ArrayList< >();

        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM posts;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            var id = resultSet.getInt("id");
            var writterid = resultSet.getInt("writterid");
            var content = resultSet.getString("content");
            var likenumber = resultSet.getInt("likenumber");
            var commentnumber = resultSet.getInt("commentnumber");
            var createdate = resultSet.getTimestamp("createdate");
            var relatedGroupId = resultSet.getInt("relatedgroupid");
            var relatedPostid = resultSet.getInt("relatedPostid");

            if (relatedPostid != -1) {  // it is a Comment
                list.add(new Comment(id, writterid, content, likenumber, commentnumber, createdate, relatedGroupId, relatedPostid));
            }
            else {  // it is a Post
                list.add(new Post(id, writterid, content, likenumber, commentnumber, createdate, relatedGroupId));
            }
        }

        return list;
    }
    
    public ArrayList<Post> getPosts(String userid) throws SQLException {
        ArrayList<Post> list = new ArrayList< >();
        var ID = Integer.parseInt(userid);

        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM posts WHERE writterid = ?;");
        statement.setInt(1, ID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            var id = resultSet.getInt("id");
            var content = resultSet.getString("content");
            var likenumber = resultSet.getInt("likenumber");
            var commentnumber = resultSet.getInt("commentnumber");
            var createdate = resultSet.getTimestamp("createdate");
            var relatedGroupId = resultSet.getInt("relatedgroupid");
            var relatedPostid = resultSet.getInt("relatedPostid");

            if (relatedPostid != -1) {  // it is a Comment
                list.add(new Comment(id, ID, content, likenumber, commentnumber, createdate, relatedGroupId, relatedPostid));
            }
            else {  // it is a Post
                list.add(new Post(id, ID, content, likenumber, commentnumber, createdate, relatedGroupId));
            }
        }

        return list;
    }

    public ArrayList<Post> getComments(String userid) throws SQLException {
        ArrayList<Post> list = new ArrayList< >();
        var ID = Integer.parseInt(userid);

        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM posts WHERE writterid = ? WHERE relatedpost != -1;");
        statement.setInt(1, ID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            var id = resultSet.getInt("id");
            var content = resultSet.getString("content");
            var likenumber = resultSet.getInt("likenumber");
            var commentnumber = resultSet.getInt("commentnumber");
            var createdate = resultSet.getTimestamp("createdate");
            var relatedGroupId = resultSet.getInt("relatedgroupid");
            var relatedPostid = resultSet.getInt("relatedPostid");

            list.add(new Comment(id, ID, content, likenumber, commentnumber, createdate, relatedGroupId, relatedPostid));
        }

        return list;
    }

    public ArrayList<Comment> getCommentsByPostID(String postid) throws SQLException {
        ArrayList<Comment> list = new ArrayList< >();
        var ID = Integer.parseInt(postid);

        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM posts WHERE relatedpost = ?;");
        statement.setInt(1, ID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            var id = resultSet.getInt("id");
            var content = resultSet.getString("content");
            var likenumber = resultSet.getInt("likenumber");
            var commentnumber = resultSet.getInt("commentnumber");
            var createdate = resultSet.getTimestamp("createdate");
            var relatedGroupId = resultSet.getInt("relatedgroupid");
            var relatedPostid = resultSet.getInt("relatedPostid");

            list.add(new Comment(id, ID, content, likenumber, commentnumber, createdate, relatedGroupId, relatedPostid));
        }

        return list;
    }
    
}
