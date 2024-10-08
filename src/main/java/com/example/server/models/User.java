package main.java.com.example.server.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User implements Serializable {

    @JsonProperty ("id")
    private int id;

    @JsonProperty ("email")
    private String email;

    @JsonProperty ("password")
    private String password;

    @JsonProperty ("firstName")
    private String  firstName;

    @JsonProperty ("lastName")
    private String lastname;

    @JsonProperty ("additinolname")
    private String additionalname;

    @JsonProperty ("headtitle")
    private String  headtitle;

    @JsonProperty ("country")
    private String country;

    @JsonProperty ("city")
    private String city;

    @JsonProperty ("requiredJob")
    private String requiredJob;


    // constructors
    public User(int id, String email, String password, String firstName, String lastname, String additionalname, String headtitle,
            String country, String city, String requiredJob) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastname = lastname;
        this.additionalname = additionalname;
        this.headtitle = headtitle;
        this.country = country;
        this.city = city;
        this.requiredJob = requiredJob;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRequiredJob() {
        return requiredJob;
    }

    public void setRequiredJob(String requiredJob) {
        this.requiredJob = requiredJob;
    }

    
    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
                + ", lastname=" + lastname + ", additionalname=" + additionalname + ", headtitle=" + headtitle
                + ", country=" + country + ", city=" + city + ", requiredJob=" + requiredJob + "]";
    }
    
}