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


    // constructors
    public SocialMedia(int id, application application) {
        this.id = id;
        Application = application;
    }

    public SocialMedia() {

    }


    // accessor and mutators
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public application getApplication() {
        return Application;
    }

    public void setApplication(application application) {
        Application = application;
    }


    @Override
    public String toString() {
        return "SocialMedia [id=" + id + ", Application=" + Application + "]";
    }
    
}
