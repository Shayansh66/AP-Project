package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SocialMedia {
    
    @JsonProperty("id")
    private int id;

    @JsonProperty ("userid")
    private int userid;

    @JsonProperty ("Application")
    private Application Application;

    @JsonProperty ("contactid")
    private String contactid;

    private enum Application {
        Telegram,
        WhatsApp,
        Skype,
        Instagram
    }


    // constructors
    public SocialMedia(int id, int userid, Application application, String contactid) {
        this.id = id;
        this.userid = userid;
        Application = application;
        this.contactid = contactid;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Application getApplication() {
        return Application;
    }

    public void setApplication(Application application) {
        Application = application;
    }

    public String getContactid() {
        return contactid;
    }

    public void setContactid(String contactid) {
        this.contactid = contactid;
    }


    @Override
    public String toString() {
        return "SocialMedia [id=" + id + ", Application=" + Application + "]";
    }
    
}
