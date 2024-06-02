package main.java.com.example.server.dataAccesses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class HashtagDAO {
    
    private Connection theConnection;

    public HashtagDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }
    
    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CRAATE TABLE IF NOT EXISTS hashtags (postid INT NOT NULL, content VARCHAR(255) PRIMARY KEY);");
        statement.executeUpdate();
    }

    public void saveHashtag(String postid, String content) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO hashtags (postid, content) VALUES (?, ?);");
        statement.setInt(1, Integer.parseInt(postid));
        statement.setString(2, content);
        statement.executeUpdate();
    }

    public void deleteHashtag(String content) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM hashtags WHERE content = ?;");
        statement.setString(1, content);
        statement.executeUpdate();
    }

    public void deleteHashtags(String postid) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM hashtags WHERE postid = ?;");
        statement.setInt(1, Integer.parseInt(postid));
        statement.executeUpdate();
    }

    public void deleteHashtags() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM hashtags;");
        statement.executeUpdate();
    }

    public ArrayList<Integer> getHashtagsPost(String postid) throws SQLException {
        ArrayList<Integer> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM hashtags WHERE postid = ?;");
        statement.setInt(1, Integer.parseInt(postid));
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            list.add(resultSet.getInt("postid"));
        }
        return list;
    }

    public ArrayList<Integer> getPosts(String content) throws SQLException {
        ArrayList<Integer> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM hashtags WHERE content = ?;");
        statement.setString(1, content);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            list.add(resultSet.getInt("postid"));
        }
        return list;
    }
    
}
