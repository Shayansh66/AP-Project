package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Follow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class FollowDAO {

    private Connection theConnection;

    public FollowDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CRATE TABLE IF NOT EXISTS follows (id INT PRIMARY KEY AUTO_INCREMENT, followerid INT NOT NULL, followingid INT NOT NULL, isconnection BOOLEAN DEFAULT FALSE);");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE follows AUTO_INCREMENT = 25000000;");
    }
    
    public void saveFollow(Follow follow) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO follows (followerid, followingid, isconnection) VALUES (?, ?, ?);");
        statement.setInt(1, follow.getFollowerid());
        statement.setInt(2, follow.getFollowingid());
        statement.setBoolean(3, follow.isConnection());
        statement.executeUpdate();
    }

    public void updateFollow(Follow follow) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE follows SET followerid = ?, followingid = ?, isconnection = ?;");
        statement.setInt(1, follow.getFollowerid());
        statement.setInt(2, follow.getFollowingid());
        statement.setBoolean(3, follow.isConnection());
        statement.executeUpdate();
    }

    public void deleteFollow(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM follows WHERE id = ?;");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void deleteFollow(Follow follow) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM follows WHERE id = ?;");
        statement.setInt(1, follow.getId());
        statement.executeUpdate();
    }

    public void deleteFollows() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM follows;");
        statement.executeUpdate();
    }

    public Follow getFollow(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM follows WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Follow follow = new Follow();
            follow.setId(resultSet.getInt("id"));
            follow.setFollowerid(resultSet.getInt("followerid"));
            follow.setFollowingid(resultSet.getInt("followingid"));
            follow.setConnection(resultSet.getBoolean("isconnection"));
            return follow;
        }

        return null;
    }
    
    public ArrayList<Follow> getFollowers(int followingid) throws SQLException {
        ArrayList<Follow> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM follows WHERE followerid = ?");
        statement.setInt(1, followingid);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Follow follow = new Follow();
            follow.setId(resultSet.getInt("id"));
            follow.setFollowerid(resultSet.getInt("followerid"));
            follow.setFollowingid(resultSet.getInt("followingid"));
            follow.setConnection(resultSet.getBoolean("isconnection"));
            list.add(follow);
        }

        return list;
    }
    
    public ArrayList<Follow> getFollowings(int followerid) throws SQLException {
        ArrayList<Follow> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM follows WHERE followerid = ?");
        statement.setInt(1, followerid);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Follow follow = new Follow();
            follow.setId(resultSet.getInt("id"));
            follow.setFollowerid(resultSet.getInt("followerid"));
            follow.setFollowingid(resultSet.getInt("followingid"));
            follow.setConnection(resultSet.getBoolean("isconnection"));
            list.add(follow);
        }

        return list;
    }
    
    public ArrayList<Follow> getFollows() throws SQLException {
        ArrayList<Follow> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM follows");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Follow follow = new Follow();
            follow.setId(resultSet.getInt("id"));
            follow.setFollowerid(resultSet.getInt("followerid"));
            follow.setFollowingid(resultSet.getInt("followingid"));
            follow.setConnection(resultSet.getBoolean("isconnection"));
            list.add(follow);
        }

        return list;
    }
    
}
