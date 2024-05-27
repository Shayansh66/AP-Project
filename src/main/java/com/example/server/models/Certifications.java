package main.java.com.example.server.models;

import java.time.LocalDate;

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

    
}
