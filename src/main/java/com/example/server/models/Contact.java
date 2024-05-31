package main.java.com.example.server.models;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Contact {

    @JsonProperty ("id")
    private int id;

    @JsonProperty ("userid")
    private int userid;

    @JsonProperty ("profilelink")
    private String profilelink;

    @JsonProperty ("email")
    private String email;

    @JsonProperty ("phonenumber")
    private String phonenumber;

    @JsonProperty ("PhoneType")
    private PhoneType phoneType;

    @JsonProperty ("address")
    private String addres;

    @JsonProperty ("birthday")
    private Timestamp birthday;

    @JsonProperty ("birthdayvisibility")
    private visibility birthdayvisibility;

    @JsonProperty ("communicationId")
    private String communicationId;

    enum visibility{
        me, 
        myContacts,
        myNetwork,
        everyBody
    }

    enum PhoneType {
        Home,
        Mobile,
        Work
    }


    // constructors
    public Contact(int id, int userid, String profilelink, String email, String phonenumber, PhoneType phoneType, String addres,
            Timestamp birthday, visibility birthdayvisibility, String communicationId) {
        this.id = id;
        this.userid = userid;
        this.profilelink = profilelink;
        this.email = email;
        this.phonenumber = phonenumber;
        this.phoneType = phoneType;
        this.addres = addres;
        this.birthday = birthday;
        this.birthdayvisibility = birthdayvisibility;
        this.communicationId = communicationId;
    }

    public Contact() {
        
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
    
    public String getProfilelink() {
        return profilelink;
    }
    
    public void setProfilelink(String profilelink) {
        this.profilelink = profilelink;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }
    
    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }
    
    public Timestamp getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }
    
    public visibility getBirthdayvisibility() {
        return birthdayvisibility;
    }

    public void setBirthdayvisibility(visibility birthdayvisibility) {
        this.birthdayvisibility = birthdayvisibility;
    }

    public String getCommunicationId() {
        return communicationId;
    }

    public void setCommunicationId(String communicationId) {
        this.communicationId = communicationId;
    }

    
    @Override
    public String toString() {
        return "Contact [id=" + id + ", userid=" + userid + ", profilelink=" + profilelink + ", email=" + email
                + ", phonenumber=" + phonenumber + ", phoneType=" + phoneType + ", addres=" + addres + ", birthday="
                + birthday + ", birthdayvisibility=" + birthdayvisibility + ", communicationId=" + communicationId
                + "]";
    }
    
}
