package main.java.com.example.server;

import com.sun.net.httpserver.HttpServer;

import main.java.com.example.server.httpHandler.SessionHandler;
import main.java.com.example.server.httpHandler.SkillHandler;
import main.java.com.example.server.httpHandler.UserHandler;
import main.java.com.example.server.httpHandler.ConnectionHandler;
import main.java.com.example.server.httpHandler.ContactHandler;
import main.java.com.example.server.httpHandler.EducationHandler;
import main.java.com.example.server.httpHandler.FollowHandler;
import main.java.com.example.server.httpHandler.LikeHandler;
import main.java.com.example.server.httpHandler.MessageHandler;
import main.java.com.example.server.httpHandler.PostHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws SQLException {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/users", new UserHandler());
            server.createContext("/educations", new EducationHandler());
            server.createContext("/follows", new FollowHandler());
            server.createContext("/likes", new LikeHandler());
            server.createContext("/messages", new MessageHandler());
            server.createContext("/skills", new SkillHandler());
            server.createContext("/sessions", new SessionHandler());
            server.createContext("/connections", new ConnectionHandler());
            server.createContext("/contacts", new ContactHandler());
            server.createContext("/posts", new PostHandler());
            server.start();

            System.out.println("Server started on port 8080");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
