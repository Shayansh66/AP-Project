package main.java.com.example.server.models;


import com.fasterxml.jackson.annotation.JsonProperty;


public class SocialMedia {
    
    @JsonProperty("id")
    private int id;

    @JsonProperty ("Application")
    private application Application;

    private enum application {
        Telegram,
        WhatsApp,
        Skype,
        Instagram
    }
}
