package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class UserDAO {

    private Connection theConnection;

    public UserDAO() throws SQLException {
        this.theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id PRIMARY KEY AUTO_INCREMENT, firstname VARCHAR(20), lastname VARCHAR(40), additionalname VARCHAR(40), headtitle VARCHAR(220), countrey VARCHAR(60), city VARCHAR(60), createdate TIMESTAMP DEFAULT NOW())");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE users AUTO_INCREMENT = 20000000");
        statement.executeUpdate();
    }

    public void saveUser(User user) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO users (firstname, lastname, additionalname, headtitle, country, city) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastname());
        statement.setString(3, user.getAdditionalname());
        statement.setString(4, user.getHeadtitle());
        statement.setString(5, user.getCountry());
        statement.setString(6, user.getCity());
        statement.executeUpdate();
    }

    public void updateUser(User user) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE users SET firstname = ?, lastname = ?, additionalname = ?, headtitle = ?, country = ?, city = ? WHERE userid = ?");
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastname());
        statement.setString(3, user.getAdditionalname());
        statement.setString(4, user.getAdditionalname());
        statement.setString(5, user.getCountry());
        statement.setString(6, user.getCity());
        statement.setInt(7, user.getId());
        statement.executeUpdate();
    }

    public void deleteUser(User user) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM users WHERE userid = ?");
        statement.setInt(1, user.getId());
        statement.executeUpdate();
    }
    
    public void deleteUser(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM users WHERE userid = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    
    public void deleteUsers() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM users");
        statement.executeUpdate();
    }
}
