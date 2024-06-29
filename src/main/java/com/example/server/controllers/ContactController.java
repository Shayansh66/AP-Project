package main.java.com.example.server.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;

import main.java.com.example.server.dataAccesses.ContactDAO;
import main.java.com.example.server.models.Contact;

public class ContactController {
    private final ContactDAO contactDAO;
    private final UserController userController;

    public ContactController () throws SQLException {
        contactDAO = new ContactDAO();
        userController = new UserController();
    }
    

    public String createContact (int id , int userId , String profileLink , String email , String phoneNumber , String phoneType , String address , Timestamp birthday  , 
    String birthdayVisibility , String communicationId  ) throws SQLException {
        if (!userController.isUserAlreadyExist(userId)) {
            return "user not found";
        }
        if (!userController.isValidEmail(email)) {
            return "not a valid emal format";
         }
        
        Contact contact =  new Contact(id, id, profileLink, email, phoneNumber, phoneType, address,birthday, birthdayVisibility, communicationId);
        contactDAO.saveContact(contact);
        return "sucsessful";
    }

    public String updateContact (int id , int userId , String profileLink , String email , String phoneNumber , String phoneType , String address , Timestamp birthday  , 
    String birthdayVisibility , String communicationId)throws SQLException {
        if (!userController.isUserAlreadyExist(userId)) {
            return "user not found";
        }
        if (!userController.isValidEmail(email)) {
           return "not a valid emal format";
        }
        
        Contact contact = new Contact(id, id, profileLink, email, phoneNumber, phoneType, address, birthday, birthdayVisibility, communicationId);
        contactDAO.updateContact(contact);
        return "sucsessful";
    }

    public void deleteContacts () throws SQLException {
        contactDAO.deleteContacts();
    }

    public void deleteContact (Contact contact) throws SQLException {
        contactDAO.deleteContact(contact);
    }

    public void deleteContact (int id) throws SQLException {
        contactDAO.deleteContact(id);
    }

    public String getContact (int id)throws SQLException, JsonProcessingException {
        Contact contact = new Contact();
        ObjectMapper ob = new ObjectMapper();
        return ob.writeValueAsString(contact);
    }

    public String getContacts () throws SQLException, JsonProcessingException {
        ArrayList <Contact> contacts = contactDAO.getContacts();
        ObjectMapper ob = new ObjectMapper();
        return ob.writeValueAsString(contacts);
    }

    public String getContactsByUserId (int userId) throws SQLException, JsonProcessingException {
        Contact userContacts = contactDAO.getContactById(userId);
        ObjectMapper ob = new ObjectMapper();
        return ob.writeValueAsString(userContacts);
        
    }
}
