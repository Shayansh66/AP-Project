package main.java.com.example.server.controllers;

import java.sql.Timestamp;
import java.sql.SQLException;

import main.java.com.example.server.dataAccesses.ContactDAO;
import main.java.com.example.server.models.Contact;

public class ContactController {
    private final ContactDAO contactDAO;

    public ContactController () throws SQLException {
        contactDAO = new ContactDAO();
    }
    

    public void createContact (int id , String userId , String profileLink , String email , String phoneNumber , String phoneType , String address , Timestamp birthday  , 
    String birthdayVisibility , String communicationId  ) throws SQLException {
        Contact contact =  new Contact(id, id, profileLink, email, phoneNumber, phoneType, address,birthday, birthdayVisibility, communicationId);
        contactDAO.saveContact(contact);
    }

    public void updateContact (int id , String userId , String profileLink , String email , String phoneNumber , String phoneType , String address , Timestamp birthday  , 
    String birthdayVisibility , String communicationId)throws SQLException {
        Contact contact = new Contact(id, id, profileLink, email, phoneNumber, phoneType, address, birthday, birthdayVisibility, communicationId);
        contactDAO.updateContact(contact);
    }

    public void deleteContacts () throws SQLException {
        contactDAO.deleteContacts();
    }

    public void deleteContact (Contact contact) throws SQLException {
        contactDAO.deleteContact(contact);
    }

    public void deleteContact (String id) throws SQLException {
        contactDAO.deleteContact(id);
    }
}
