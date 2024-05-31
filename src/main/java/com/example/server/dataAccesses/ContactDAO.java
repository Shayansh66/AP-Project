package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ContactDAO {
    
    private Connection thConnection;

    public ContactDAO() throws SQLException {
        thConnection = DatabaseConnectionManager.getTheConnection();
        creatTable();
    }

    public void creatTable() throws SQLException {
        PreparedStatement statement = thConnection.prepareStatement("CREATE TABLE IF NOT EXISTS contacts (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOT NULL, profilelink VARCHAR(255), email VARCHAR(255), phonenumber VARCHAR(40), phonetype VARCHAR(40), address VARCHAR(220), birthday TIMESTAMP, birthdayvisibility VARCHAR(40), communicationid VARCHAR(255);");
        statement.executeUpdate();
        statement = thConnection.prepareStatement("ALTER TABLE contacts AUTO_INCREMENT = 46000000;");
        statement.executeUpdate();
    }

    public void saveContact(Contact contact) throws SQLException {
        PreparedStatement statement = thConnection.prepareStatement("INSERT INTO contacts (userid, profilelink, email, phonenumber, phonetype, address, birthday, birthdayvisibility, communicationId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        statement.setInt(1, contact.getUserid());
        statement.setString(2, contact.getProfilelink());
        statement.setString(3, contact.getEmail());
        statement.setString(4, contact.getPhonenumber());
        statement.setString(5, contact.getPhoneType());
        statement.setString(6, contact.getAddres());
        statement.setTimestamp(7, contact.getBirthday());
        statement.setString(8, contact.getbirthDayVisibility());
        statement.setString(9, contact.getCommunicationId());
        statement.executeUpdate();
    }

    public void updateContact(Contact contact) throws SQLException {
        PreparedStatement statement = thConnection.prepareStatement("UPDATE contacts userid = ?, profilelink = ?, email = ?, phonenumber = ?, phonetype = ?, address = ?, birthday = ?, birthdayvisibility = ?, communicationId = ? WHERE id = ?;");
        statement.setInt(1, contact.getUserid());
        statement.setString(2, contact.getProfilelink());
        statement.setString(3, contact.getEmail());
        statement.setString(4, contact.getPhonenumber());
        statement.setString(5, contact.getPhoneType());
        statement.setString(6, contact.getAddres());
        statement.setTimestamp(7, contact.getBirthday());
        statement.setString(8, contact.getbirthDayVisibility());
        statement.setString(9, contact.getCommunicationId());
        statement.setInt(9, contact.getId());
        statement.executeUpdate();
    }

    public void deleteContact(Contact contact) throws SQLException {
        PreparedStatement statement = thConnection.prepareStatement("DELETE FROM contacts WHERE id = ?;");
        statement.setInt(1, contact.getId());
        statement.executeUpdate();
    }

    public void deleteContact(String id) throws SQLException {
        PreparedStatement statement = thConnection.prepareStatement("DELETE FROM contacts WHERE id = ?;");
        statement.setInt(1, Integer.parseInt(id));
        statement.executeUpdate();
    }

    public void deleteContacts() throws SQLException {
        PreparedStatement statement = thConnection.prepareStatement("DELETE FROM contacts;");
        statement.executeUpdate();
    }
    
}
