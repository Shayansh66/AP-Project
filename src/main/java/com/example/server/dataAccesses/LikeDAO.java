package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LikeDAO {
    
    private Connection theConnection;

    public LikeDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }
    
    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS likes (id INT PRIMARY KEY AUTO_INCREMENT, likerid INT NOT NULL, likedpostid INT NOT NULL);");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE likes AUTO_INCREMENT = 70000000;");
        statement.executeUpdate();
    }
    
    public void saveLike(Like like) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO likes (likerid, likedpostid) VALUES (?, ?);");
        statement.setInt(1, like.getLikerid());
        statement.setInt(2, like.getLikedPostid());
        statement.executeUpdate();
    }

    public void deleteLike(Like like) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM likes WHERE id = ?;");
        statement.setInt(1, like.getId());
        statement.executeUpdate();
    }

    public void deleteLikes() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM likes;");
        statement.executeUpdate();
    }

    public boolean isLikeing(int likerid, int likedPostid) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM likes WHERE likerid = ? AND likedpostid = ?;");
        statement.setInt(1, likerid);
        statement.setInt(2, likedPostid);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return true;
        }
        else {
            return false;
        }
    }
    
}
