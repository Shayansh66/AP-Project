package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User {

    @JsonProperty ("id")
    private int id;

    @JsonProperty ("firstName")
    private String  firstName;

    @JsonProperty ("lastname")
    private String lastname;

    @JsonProperty ("additinolname")
    private String additionalname;

    @JsonProperty ("headtitle")
    private String  headtitle;

    @JsonProperty ("country")
    private String country;

    @JsonProperty ("city")
    private String city;


    // constructors
    public User(int id, String firstName, String lastname, String additionalname, String headtitle) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.additionalname = additionalname;
        this.headtitle = headtitle;
    }

    public User() {
    }


    // accessor and mutators
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getAdditionalname() {
        return additionalname;
    }
    
    public void setAdditionalname(String additionalname) {
        this.additionalname = additionalname;
    }
    
    public String getHeadtitle() {
        return headtitle;
    }
    
    public void setHeadtitle(String headtitle) {
        this.headtitle = headtitle;
    }
    
    
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + firstName + ", lastname=" + lastname + ", additionalname=" + additionalname
                + ", headtitle=" + headtitle + "]";
    }
    
}