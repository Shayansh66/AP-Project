package main.java.com.example.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.example.server.dataAccesses.UserDAO;
import main.java.com.example.server.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserController {

   private final UserDAO userDAO;
   private final ObjectMapper objectMapper;
   private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
   private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

   public UserController() throws SQLException {
      userDAO = new UserDAO();
      objectMapper = new ObjectMapper();
   }

   public String createUser(int id, String password, String email, String firstName, String lastName, String additionalName,
    String headTitle, String country, String city, String requiredJob) throws SQLException {
        if (userDAO.getUserByEmail(email) != null) {
            return "Email already in use";
        }
        if (!isValidEmail(email)) {
          return "invalid email format";
        }
        if (!isValidPassword(password)) {
          return "invalid password format";
        }
        User user = new User(id, email, password, firstName, lastName, additionalName, headTitle, country, city, requiredJob);
        userDAO.saveUser(user);
        return "succsesful";
   }

   public String UpdateUser (int id, String password, String email, String firstName, String lastName, String additionalName,
   String headTitle, String country, String city, String requiredJob) throws SQLException {
    if (userDAO.getUserById(id) == null) {
        return" no user found with this id";
    }
      if (!isValidEmail(email)) {
        return "invalid email format";
      }
      if (!isValidPassword(password)) {
        return "invalid password format";
      }
        User user = new User(id, email, password, firstName, lastName, additionalName, headTitle, country, city, requiredJob);
        userDAO.updateUser(user);
        return "sucsessful";
   }

   public String deleteUser(int id) throws SQLException {
    if (isUserAlreadyExist(id)) {
        userDAO.deleteUser(id);
        return "succsessful";
    }  
    else 
    return "no user found with this id";
   }

   public void deleteUser(User user) throws SQLException {
      userDAO.deleteUser(user);
   }

   public void deleteUsers() throws SQLException {
      userDAO.deleteUsers();
   }
   public String getUser (String email, String password) throws SQLException , JsonProcessingException {
    User user = userDAO.getUser(email, password);
    if (user == null) {
        return "the email and password is incorrect";
    }
    return objectMapper.writeValueAsString(user);
   }

   public String getUser (int id, String password) throws SQLException , JsonProcessingException {
    User user = userDAO.getUser(id, password);
    if (user == null) {
        return "the email and password is incorrect";
    }
    return objectMapper.writeValueAsString(user);
   }
  

   public String getUserById(int id) throws SQLException, JsonProcessingException {
      User user = userDAO.getUserById(id);
      if (user == null) return "No User Found";
      return objectMapper.writeValueAsString(user);
    }

   public String getUserByEmail(String email) throws SQLException, JsonProcessingException {
      if (!isValidEmail(email)) {
         throw new IllegalArgumentException("Invalid email format");
      }
      User user = userDAO.getUserByEmail(email);
      if (user == null) return "No User Found";
      return objectMapper.writeValueAsString(user);
   }
 
   public String getUsers() throws SQLException, JsonProcessingException {
      ArrayList<User> users = userDAO.getUsers();
      return objectMapper.writeValueAsString(users);
   }

   

   public static boolean isValidEmail(String email) {
      Pattern pattern = Pattern.compile(EMAIL_PATTERN);
      Matcher matcher = pattern.matcher(email);
      return matcher.matches();
   }

   public static boolean isValidPassword(String password) {
      Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
      Matcher matcher = pattern.matcher(password);
      return matcher.matches();
   }


   public Boolean isUserAlreadyExist (int id) {
    try {
        return (userDAO.getUserById(id))!= null;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        
    
   }
}
