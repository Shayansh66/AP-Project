package main.java.com.example.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.example.server.dataAccesses.UserDAO;
import main.java.com.example.server.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.RuntimeErrorException;

public class UserController {

   private final UserDAO userDAO;
   private final ObjectMapper objectMapper;
   private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
   private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

   public UserController() throws SQLException {
      userDAO = new UserDAO();
      objectMapper = new ObjectMapper();
   }

   public void createUser(String id, String password, String email, String firstName, String lastName, String additionalName,
    String headTitle, String country, String city, String requiredJob) throws SQLException {
        
        if (isUserAlreadyExist(id)) {
            
        }

       if (!isValidEmail(email)) {
         throw new IllegalArgumentException("Invalid email format");
       }
       if (!isValidPassword(password)) {
         throw new IllegalArgumentException("Invalid password format");
       }
       User user = new User(id, email, password, firstName, lastName, additionalName, headTitle, country, city, requiredJob);
       userDAO.saveUser(user);
   }

   public void deleteUser(int id) throws SQLException {
      userDAO.deleteUser(id);
   }

   public void deleteUser(User user) throws SQLException {
      userDAO.deleteUser(user);
   }

   public void deleteUsers() throws SQLException {
      userDAO.deleteUsers();
   }

  

   public String getUserById(String id) throws SQLException, JsonProcessingException {
      User user = userDAO.getUserById(id);
      if (user == null) return "No User Found";
      return convertUserToJson(user);
   }

   public String getUserByEmail(String email) throws SQLException, JsonProcessingException {
      if (!isValidEmail(email)) {
         throw new IllegalArgumentException("Invalid email format");
      }
      User user = userDAO.getUserByEmail(email);
      if (user == null) return "No User Found";
      return convertUserToJson(user);
   }

   public String getUsers() throws SQLException, JsonProcessingException {
      ArrayList<User> users = userDAO.getUsers();
      return objectMapper.writeValueAsString(users);
   }

   private String convertUserToJson(User user) throws JsonProcessingException {
      return objectMapper.writeValueAsString(user);
   }

   private boolean isValidEmail(String email) {
      Pattern pattern = Pattern.compile(EMAIL_PATTERN);
      Matcher matcher = pattern.matcher(email);
      return matcher.matches();
   }

   private boolean isValidPassword(String password) {
      Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
      Matcher matcher = pattern.matcher(password);
      return matcher.matches();
   }


   private Boolean isUserAlreadyExist (String id) {
    if (id == null) return false;

    try {
        return (userDAO.getUserById(id))!= null;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        
    
   }
}
