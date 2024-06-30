package main.java.com.example.server.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.example.server.dataAccesses.MessageDAO;
import main.java.com.example.server.dataAccesses.UserDAO;
import main.java.com.example.server.models.Message;

public class MessageController {
    private final MessageDAO messageDAO ;
    private final ObjectMapper objectMapper;
    private final UserDAO userDAO;
    
    public MessageController()throws SQLException {
        messageDAO = new MessageDAO();
        objectMapper = new ObjectMapper();
        userDAO = new UserDAO();
    }

    public String createMessage(int id, int senderid, int recieverid, String context, String[] multiMedia) throws SQLException {
        if (userDAO.getUserById(senderid) == null || userDAO.getUserById(recieverid) == null ) {
            return "sender or reciever not found";
        }
        else {
            Message message = new Message(id, senderid, recieverid, context, multiMedia);
            messageDAO.saveMessage(message);
            return "Sucsessful";
        }
    }

    public String  deleteMessage (int id) throws SQLException {
        if(messageDAO.getMessage(id)==null)
        {
            return "message not found with this id ";
        }
        else {
            messageDAO.deleteMessage(id);
            return "sucsessful";
        }
    }

    public void deleteMessage (Message message ) throws SQLException{
       messageDAO.deleteMessage(message); 
    }

    public String getMessage (int id) throws SQLException , JsonProcessingException {
        Message message = messageDAO.getMessage(id);
        return objectMapper.writeValueAsString(message);
    }

    public String getSentMessages (int userid) throws SQLException , JsonProcessingException {
        ArrayList <Message> sentMessages = messageDAO.getSentMessages(userid);
        return objectMapper.writeValueAsString(sentMessages);
    }

    public String GetRecievedMessages (int userid ) throws SQLException ,JsonProcessingException {
        ArrayList <Message> recievedMessages = messageDAO.getRecievedMessages(userid);
        return objectMapper.writeValueAsString(recievedMessages);
    }
}
