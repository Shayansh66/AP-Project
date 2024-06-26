package main.java.com.example.server.controllers;

import main.java.com.example.server.dataAccesses.ConnectDAO;
import main.java.com.example.server.models.Follow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectController {

    private final ConnectDAO connectDAO ;
    private final ObjectMapper objectMapper;

    public ConnectController() throws SQLException {
        this.connectDAO = new ConnectDAO(); 
        this.objectMapper = new ObjectMapper();
    } 

    public void createConnection (int userid1 , int userid2) throws SQLException {
        connectDAO.createConnection(userid1, userid2);
    }

    public void deleteConnection (int userid1 , int userid2 ) throws SQLException {
        connectDAO.deleteConnection(userid1, userid2);
    } 

}