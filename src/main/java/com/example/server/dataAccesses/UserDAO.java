package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class UserDAO {

    private Connection theConnection;

    public UserDAO() throws SQLException {
        this.theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, email VARCHAR(255) NOT NULL, password INT, firstname VARCHAR(20), lastname VARCHAR(40), additionalname VARCHAR(40), headtitle VARCHAR(220), country VARCHAR(60), city VARCHAR(60), requiredjob VARCHAR(35), createdate TIMESTAMP DEFAULT NOW());");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE users AUTO_INCREMENT = 20000000");
        statement.executeUpdate();
    }

    public void saveUser(User user) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO users (email, password, firstname, lastname, additionalname, headtitle, country, city, requiredjob) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        statement.setString(1, user.getEmail());
        statement.setInt(2, user.getPassword().hashCode());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getLastname());
        statement.setString(5, user.getAdditionalname());
        statement.setString(6, user.getHeadtitle());
        statement.setString(7, user.getCountry());
        statement.setString(8, user.getCity());
        statement.setString(9, user.getRequiredJob());
        statement.executeUpdate();
    }

    public void updateUser(User user) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE users SET password = ?, firstname = ?, lastname = ?, additionalname = ?, headtitle = ?, country = ?, city = ?, requiredjob = ? WHERE userid = ?;");
        statement.setInt(1, user.getPassword().hashCode());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastname());
        statement.setString(4, user.getAdditionalname());
        statement.setString(5, user.getAdditionalname());
        statement.setString(6, user.getCountry());
        statement.setString(7, user.getCity());
        statement.setString(8, user.getRequiredJob());
        statement.setInt(9, user.getId());
        statement.executeUpdate();
    }

    public void deleteUser(User user) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM users WHERE userid = ?;");
        statement.setInt(1, user.getId());
        statement.executeUpdate();
    }
    
    public void deleteUser(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM users WHERE userid = ?;");
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    
    public void deleteUsers() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM users;");
        statement.executeUpdate();
    }

    public User getUserById(int userid) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM users WHERE id = ?;");
        statement.setInt(1, userid);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(null);
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setAdditionalname(resultSet.getString("additionalname"));
            user.setHeadtitle(resultSet.getString("headtitle"));
            user.setCountry(resultSet.getString("country"));
            user.setCity(resultSet.getString("city"));
            user.setRequiredJob(resultSet.getString("requiredjob"));
            return user;
        }

        return null;
    }
    
    public User getUserByEmail(String email) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM users WHERE email = ?;");
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(null);
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setAdditionalname(resultSet.getString("additionalname"));
            user.setHeadtitle(resultSet.getString("headtitle"));
            user.setCountry(resultSet.getString("country"));
            user.setCity(resultSet.getString("city"));
            user.setRequiredJob(resultSet.getString("requiredjob"));
            return user;
        }

        return null;
    }

    public User getUser(int id, String password) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM users WHERE password = ? AND id = ?;");
        statement.setInt(1, password.hashCode());
        statement.setInt(2, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(null);
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setAdditionalname(resultSet.getString("additionalname"));
            user.setHeadtitle(resultSet.getString("headtitle"));
            user.setCountry(resultSet.getString("country"));
            user.setCity(resultSet.getString("city"));
            user.setRequiredJob(resultSet.getString("requiredjob"));
            return user;
        }

        return null;
    }

    public User getUser(String email, String password) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM users WHERE password = ? AND email = ?;");
        statement.setInt(1, password.hashCode());
        statement.setString(2, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(null);
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setAdditionalname(resultSet.getString("additionalname"));
            user.setHeadtitle(resultSet.getString("headtitle"));
            user.setCountry(resultSet.getString("country"));
            user.setCity(resultSet.getString("city"));
            user.setRequiredJob(resultSet.getString("requiredjob"));
            return user;
        }

        return null;
    }

    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> list = new ArrayList< >();

        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM users;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(null);
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setAdditionalname(resultSet.getString("additionalname"));
            user.setHeadtitle(resultSet.getString("headtitle"));
            user.setCountry(resultSet.getString("country"));
            user.setCity(resultSet.getString("city"));
            user.setRequiredJob(resultSet.getString("requiredjob"));
            list.add(user);
        }

        return list;
    }
    
}
