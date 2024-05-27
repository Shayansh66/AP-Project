package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Profession {

    @JsonProperty ("id")
    private int id;

    @JsonProperty ("userId")
    private int userId;

    @JsonProperty ("text")
    private String text;
    

    // constructors
    public Profession(int id, int userId, String text) {
        this.id = id;
        this.userId = userId;
        this.text = text;
    }

    public Profession() {

    }

    
    // accessor and mutators
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "Profession [id=" + id + ", userId=" + userId + ", text=" + text + "]";
    }
    
}
