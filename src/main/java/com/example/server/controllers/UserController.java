package main.java.com.example.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.example.server.dataAccesses.UserDAO;
import main.java.com.example.server.models.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    private final UserDAO userDAO;

    public UserController () throws SQLException {
        userDAO = new UserDAO();

    }

    public void createUser (int id , int password ,String email , String firstname , String lastname , String additionalname
     , String headtitle , String country , String city , String requiredJob  ) throws SQLException {
        User user = new User(id, email, password, firstname, lastname, additionalname, headtitle, country, city, requiredJob);
            userDAO.saveUser(user);
     }

     public void deledteUser (int id ) throws SQLException {
        User user = new User();
        user.setId(id);
        userDAO.deleteUser(id);; 
     }

     public void deledteUser (User user) throws SQLException {
        userDAO.deleteUser(user);
     }

     public void deledteUsers() throws SQLException {
        userDAO.deleteUsers();
     }

     public void updateUser (int id , int password ,String email , String firstname , String lastname , String additionalname
     , String headtitle , String country , String city , String requiredJob  ) throws SQLException {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstname);
        user.setLastname(lastname);
        user.setAdditionalname(additionalname);
        user.setPassword(password);
        user.setHeadtitle(headtitle);
        user.setEmail(email);
        user.setCountry(country);
        user.setCity(city);
        user.setRequiredJob(requiredJob);
     }

     public String getUserbyid (String id) throws SQLException, JsonProcessingException {
        User user = userDAO.getUser(id);
        ObjectMapper ob = new ObjectMapper() ;
        return ob.writeValueAsString(user);

     }
     public String getUsers () throws SQLException, JsonProcessingException {
        ArrayList <User> users = userDAO.getUsers();
        ObjectMapper ob =  new ObjectMapper();

        return ob.writeValueAsString(users);
     }

}
