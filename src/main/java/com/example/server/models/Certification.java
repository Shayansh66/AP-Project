package main.java.com.example.server.models;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Certification {

    @JsonProperty("id")
    private int id;

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("institution")
    private String institution;

    @JsonProperty("issueDate")
    private Timestamp issueDate;

    @JsonProperty ("expireDate")
    private Timestamp expireDate;

    @JsonProperty ("creditId")
    private String creditId;

    @JsonProperty ("refrenceWebiste")
    private String refrenceWebsite;
    
    
    // constructors
    public Certification(int id, int userId, String name, String institution, Timestamp issueDate, Timestamp expireDate, String creditId,
            String refrenceWebsite) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.institution = institution;
        this.issueDate = issueDate;
        this.expireDate = expireDate;
        this.creditId = creditId;
        this.refrenceWebsite = refrenceWebsite;
    }

    public Certification() {

    }


    // accessor and mutators
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Timestamp getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getRefrenceWebsite() {
        return refrenceWebsite;
    }

    public void setRefrenceWebsite(String refrenceWebsite) {
        this.refrenceWebsite = refrenceWebsite;
    }

    public Timestamp getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Timestamp expireDate) {
        this.expireDate = expireDate;
    }


    @Override
    public String toString() {
        return "Certifications [id=" + id + ", userId=" + userId + ", name=" + name + ", institution=" + institution
                + ", issueDate=" + issueDate + ", expireDate=" + expireDate + ", creditId=" + creditId
                + ", refrenceWebsite=" + refrenceWebsite + "]";
    }

}
