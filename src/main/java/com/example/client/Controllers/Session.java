package main.java.com.example.client.Controllers;

import main.java.com.example.server.models.User;

public class Session {
    private static Session instance;
    private User loggedInUser;

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
