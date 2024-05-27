package main.java.com.example.server.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {

    @JsonProperty ("id")
    private int id ;

    @JsonProperty ("userid")
    private int userid;

    @JsonProperty ("profilelink")
    private String profilelink;

    @JsonProperty ("email")
    private String email;
     @JsonProperty ("phonenumbers")
     private String phonenumbers;

     @JsonProperty ("address")
     private String addres;

     @JsonProperty ("birthday")
     private LocalDate birthday;

     @JsonProperty ("birthdayvisibility")
     private visibility birthdayvisibility;
     
    

     @Override
    public String toString() {
        return "Contact [id=" + id + ", userid=" + userid + ", profilelink=" + profilelink + ", email=" + email
                + ", phonenumbers=" + phonenumbers + ", addres=" + addres + ", birthday=" + birthday
                + ", birthdayvisibility=" + birthdayvisibility + "]";
    }



    public Contact() {
    }



    public Contact(int id, int userid, String profilelink, String email, String phonenumbers, String addres,
            LocalDate birthday, visibility birthdayvisibility) {
        this.id = id;
        this.userid = userid;
        this.profilelink = profilelink;
        this.email = email;
        this.phonenumbers = phonenumbers;
        this.addres = addres;
        this.birthday = birthday;
        this.birthdayvisibility = birthdayvisibility;
    }



    enum visibility{
        me, 
        myContacts,
        myNetwork,
        everyBody
     }



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



    public String getPhonenumbers() {
        return phonenumbers;
    }



    public void setPhonenumbers(String phonenumbers) {
        this.phonenumbers = phonenumbers;
    }



    public String getAddres() {
        return addres;
    }



    public void setAddres(String addres) {
        this.addres = addres;
    }



    public LocalDate getBirthday() {
        return birthday;
    }



    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }



    public visibility getBirthdayvisibility() {
        return birthdayvisibility;
    }



    public void setBirthdayvisibility(visibility birthdayvisibility) {
        this.birthdayvisibility = birthdayvisibility;
    }
}
