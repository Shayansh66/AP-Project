package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;












public class User {
    @JsonProperty ("id")
    private String id;

    @JsonProperty ("name")
    private String  name;

    @JsonProperty ("lastname")
    private String lastname;

    @JsonProperty ("additinolname")
    private String additionalname;

    @JsonProperty ("headtitle")
    private String  headtitle;

    





    public static void main(String[] args) {

    }
}