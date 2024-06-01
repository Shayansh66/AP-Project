package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ContactDAO {
    
    private Connection theConnection;

    public ContactDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS contacts (id INT PRIMARY KEY AUTO_INCREMENT, userid INT NOT NULL, profilelink VARCHAR(255), email VARCHAR(255), phonenumber VARCHAR(40), phonetype VARCHAR(40), address VARCHAR(220), birthday TIMESTAMP, birthdayvisibility VARCHAR(40), communicationid VARCHAR(255);");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE contacts AUTO_INCREMENT = 46000000;");
        statement.executeUpdate();
    }

    public void saveContact(Contact contact) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO contacts (userid, profilelink, email, phonenumber, phonetype, address, birthday, birthdayvisibility, communicationId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        statement.setInt(1, contact.getUserid());
        statement.setString(2, contact.getProfilelink());
        statement.setString(3, contact.getEmail());
        statement.setString(4, contact.getPhonenumber());
        statement.setString(5, contact.getPhoneType());
        statement.setString(6, contact.getAddress());
        statement.setTimestamp(7, contact.getBirthday());
        statement.setString(8, contact.getBirthDayVisibility());
        statement.setString(9, contact.getCommunicationId());
        statement.executeUpdate();
    }

    public void updateContact(Contact contact) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("UPDATE contacts userid = ?, profilelink = ?, email = ?, phonenumber = ?, phonetype = ?, address = ?, birthday = ?, birthdayvisibility = ?, communicationId = ? WHERE id = ?;");
        statement.setInt(1, contact.getUserid());
        statement.setString(2, contact.getProfilelink());
        statement.setString(3, contact.getEmail());
        statement.setString(4, contact.getPhonenumber());
        statement.setString(5, contact.getPhoneType());
        statement.setString(6, contact.getAddress());
        statement.setTimestamp(7, contact.getBirthday());
        statement.setString(8, contact.getBirthDayVisibility());
        statement.setString(9, contact.getCommunicationId());
        statement.setInt(9, contact.getId());
        statement.executeUpdate();
    }

    public void deleteContact(Contact contact) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM contacts WHERE id = ?;");
        statement.setInt(1, contact.getId());
        statement.executeUpdate();
    }

    public void deleteContact(String id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM contacts WHERE id = ?;");
        statement.setInt(1, Integer.parseInt(id));
        statement.executeUpdate();
    }

    public void deleteContacts() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM contacts;");
        statement.executeUpdate();
    }
    
    public Contact getContactById(String id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM contacts WHERE id = ?;");
        statement.setInt(1, Integer.parseInt(id));
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Contact contact = new Contact();
            contact.setId(resultSet.getInt("id"));
            contact.setUserid(resultSet.getInt("userid"));
            contact.setProfilelink(resultSet.getString("profilelink"));
            contact.setEmail(resultSet.getString("email"));
            contact.setPhonenumber(resultSet.getString("phonenumber"));
            contact.setPhoneType(resultSet.getString("phonetype"));
            contact.setAddress(resultSet.getString("address"));
            contact.setBirthday(resultSet.getTimestamp("birthday"));
            contact.setBirthday(resultSet.getTimestamp("birthday"));
            contact.setBirthDayVisibility(resultSet.getString("birthdayvisibility"));
            contact.setCommunicationId(resultSet.getString("communicationidsetCommunicationId(id);"));
            return contact;
        }

        return null;
    }

    public Contact getContactByUserId(String userid) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM contacts WHERE userid = ?;");
        statement.setInt(1, Integer.parseInt(userid));
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Contact contact = new Contact();
            contact.setId(resultSet.getInt("id"));
            contact.setUserid(resultSet.getInt("userid"));
            contact.setProfilelink(resultSet.getString("profilelink"));
            contact.setEmail(resultSet.getString("email"));
            contact.setPhonenumber(resultSet.getString("phonenumber"));
            contact.setPhoneType(resultSet.getString("phonetype"));
            contact.setAddress(resultSet.getString("address"));
            contact.setBirthday(resultSet.getTimestamp("birthday"));
            contact.setBirthday(resultSet.getTimestamp("birthday"));
            contact.setBirthDayVisibility(resultSet.getString("birthdayvisibility"));
            contact.setCommunicationId(resultSet.getString("communicationidsetCommunicationId(id);"));
            return contact;
        }

        return null;
    }

    public ArrayList<Contact> getContacts() throws SQLException {
        ArrayList<Contact> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM contacts;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Contact contact = new Contact();
            contact.setId(resultSet.getInt("id"));
            contact.setUserid(resultSet.getInt("userid"));
            contact.setProfilelink(resultSet.getString("profilelink"));
            contact.setEmail(resultSet.getString("email"));
            contact.setPhonenumber(resultSet.getString("phonenumber"));
            contact.setPhoneType(resultSet.getString("phonetype"));
            contact.setAddress(resultSet.getString("address"));
            contact.setBirthday(resultSet.getTimestamp("birthday"));
            contact.setBirthday(resultSet.getTimestamp("birthday"));
            contact.setBirthDayVisibility(resultSet.getString("birthdayvisibility"));
            contact.setCommunicationId(resultSet.getString("communicationidsetCommunicationId(id);"));
            list.add(contact);
        }

        return list;
    }
    
}
