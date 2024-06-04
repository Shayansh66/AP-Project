package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SocialMedia {
    
    @JsonProperty("id")
    private int id;

    @JsonProperty ("userid")
    private int userid;

    @JsonProperty ("application")
    private String application;

    @JsonProperty ("contactid")
    private String contactid;



    // constructors
    public SocialMedia(int id, int userid, String application, String contactid) {
        this.id = id;
        this.userid = userid;
        this.application = application;
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

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getContactid() {
        return contactid;
    }

    public void setContactid(String contactid) {
        this.contactid = contactid;
    }


    @Override
    public String toString() {
        return "SocialMedia [id=" + id + ", Application=" + application + "]";
    }
    
}
