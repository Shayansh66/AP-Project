package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profession {
    @JsonProperty ("id")
    private int id;

    @JsonProperty ("userId")
    private int userId;

    @JsonProperty ("text")
    private String text;
    

    
}
