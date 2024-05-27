package main.java.com.example.server.models;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Certifications {

    @JsonProperty("id")
    private int id;

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("institution")
    private String institution;

    @JsonProperty("issueDate")
    private LocalDate issueDate;

    @JsonProperty ("creditId")
    private String creditId;

    @JsonProperty ("refrenceWebiste")
    private String refrenceWebsite;

    @JsonProperty ("skills")
    private String[] skills;
    
    

    @Override
    public String toString() {
        return "Certifications [id=" + id + ", userId=" + userId + ", name=" + name + ", institution=" + institution
                + ", issueDate=" + issueDate + ", creditId=" + creditId + ", refrenceWebsite=" + refrenceWebsite
                + ", skills=" + Arrays.toString(skills) + "]";
    }

    public Certifications(int id, int userId, String name, String institution, LocalDate issueDate, String creditId,
            String refrenceWebsite, String[] skills) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.institution = institution;
        this.issueDate = issueDate;
        this.creditId = creditId;
        this.refrenceWebsite = refrenceWebsite;
        this.skills = skills;
    }

    public Certifications() {
    }

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

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
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

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }
    


}
