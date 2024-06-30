package main.java.com.example.server.dataAccesses;

import main.java.com.example.server.models.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MessageDAO {

    private Connection theConnection;

    public MessageDAO() throws SQLException {
        theConnection = DatabaseConnectionManager.getTheConnection();
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("CREATE TABLE IF NOT EXISTS messages (id INT PRIMARY KEY AUTO_INCREMENT, senderid INT NOT NULL, recieverid INT NOT NULL, context VARCHAR(1900));");
        statement.executeUpdate();
        statement = theConnection.prepareStatement("ALTER TABLE messages AUTO_INCREMENT = 71000000;");
        statement.executeUpdate();
    }

    public void saveMessage(Message message) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("INSERT INTO messages (senderid, recieverid, context) VALUS (?, ?, ?);");
        statement.setInt(1, message.getSenderid());
        statement.setInt(2, message.getRecieverid());
        statement.setString(3, message.getContext());
        statement.executeUpdate();
    }

    public void deleteMessage(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM messages WHERE id = ?;");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void deleteMessage(Message message) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("DELETE FROM messages WHERE id = ?;");
        statement.setInt(1, message.getId());
        statement.executeUpdate();
    }

    public Message getMessage(int id) throws SQLException {
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM messages WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getInt("id"));
            message.setSenderid(resultSet.getInt("senderid"));
            message.setRecieverid(resultSet.getInt("recieverid"));
            message.setContext(resultSet.getString("context"));
            return message;
        }

        return null;
    }
    
    public ArrayList<Message> getSentMessages(int userid) throws SQLException {
        ArrayList<Message> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM messages WHERE senderid = ?;");
        statement.setInt(1, userid);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getInt("id"));
            message.setSenderid(resultSet.getInt("senderid"));
            message.setRecieverid(resultSet.getInt("recieverid"));
            message.setContext(resultSet.getString("context"));
            list.add(message);
        }

        return list;
    }

    public ArrayList<Message> getRecievedMessages(int userid) throws SQLException {
        ArrayList<Message> list = new ArrayList< >();
        PreparedStatement statement = theConnection.prepareStatement("SELECT * FROM messages WHERE recieverid = ?;");
        statement.setInt(1, userid);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getInt("id"));
            message.setSenderid(resultSet.getInt("senderid"));
            message.setRecieverid(resultSet.getInt("recieverid"));
            message.setContext(resultSet.getString("context"));
            list.add(message);
        }

        return list;
    }

}
