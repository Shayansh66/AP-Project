package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.SocialMedia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class SocialMediaDAO {
    
    private Connection theConnection;

    public SocialMediaDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }
    
    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS socialmedias (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOT NULL, application VARCHAR(40), contactid VARCHAR(255) NOT NULL);");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE socialmedias AUTO_INCREMENT = 48000000;");
        statement.executeUpdate();
    }

    public void saveSocialMedia(SocialMedia socialMedia) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO socialmedias (userid, application, contactid) VALUES (?, ?, ?);");
        statement.setInt(1, socialMedia.getUserid());
        statement.setString(2, socialMedia.getApplication());
        statement.setString(3, socialMedia.getContactid());
        statement.executeUpdate();
    }

    public void updateSocialMedia(SocialMedia socialMedia) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE socialmedias SET userid = ?, application = ?, contactid = ?;");
        statement.setInt(1, socialMedia.getUserid());
        statement.setString(2, socialMedia.getApplication());
        statement.setString(3, socialMedia.getContactid());
        statement.setInt(4, socialMedia.getId());
        statement.executeUpdate();
    }

    public void deleteSocialMedia(SocialMedia socialMedia) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM socialmedias WHERE id = ?;");
        statement.setInt(1, socialMedia.getId());
        statement.executeUpdate();
    }

    public void deleteSocialMedia(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM socialmedias WHERE id = ?;");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void deleteSocialMedias() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM socialmedias;");
        statement.executeUpdate();
    }

    public SocialMedia getSocialMedia(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM socialmedias WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            SocialMedia socialMedia = new SocialMedia();
            socialMedia.setId(resultSet.getInt("id"));
            socialMedia.setUserid(resultSet.getInt("userid"));
            socialMedia.setApplication(resultSet.getString("application"));
            socialMedia.setContactid(resultSet.getString("contactid"));
            return socialMedia;
        }

        return null;
    }

    public ArrayList<SocialMedia> getSocialMediasByUserId(int userid) throws SQLException {
        ArrayList<SocialMedia> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM socialmedias WHERE userid = ?;");
        statement.setInt(1, userid);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            SocialMedia socialMedia = new SocialMedia();
            socialMedia.setId(resultSet.getInt("id"));
            socialMedia.setUserid(resultSet.getInt("userid"));
            socialMedia.setApplication(resultSet.getString("application"));
            socialMedia.setContactid(resultSet.getString("contactid"));
            list.add(socialMedia);
        }

        return list;
    }
    
    public ArrayList<SocialMedia> getSocialMedias() throws SQLException {
        ArrayList<SocialMedia> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM socialmedias;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            SocialMedia socialMedia = new SocialMedia();
            socialMedia.setId(resultSet.getInt("id"));
            socialMedia.setUserid(resultSet.getInt("userid"));
            socialMedia.setApplication(resultSet.getString("application"));
            socialMedia.setContactid(resultSet.getString("contactid"));
            list.add(socialMedia);
        }

        return list;
    }
    
}
