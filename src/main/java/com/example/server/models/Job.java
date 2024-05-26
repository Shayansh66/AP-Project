package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public class Job {

    @JsonProperty ("id")
    private String id;

    @JsonProperty ("title")
    private String title;
    
    @JsonProperty ("companyname")
    private String companyname;

    @JsonProperty ("workplace")
    private String workplace;

    @JsonProperty ("isworking")
    private boolean isworking;

    @JsonProperty ("worktype")
    private Worktype worktype;

    @JsonProperty ("jobtype")
    private Jobtype jobtype;

    @JsonProperty ("startdate")
    private Timestamp startDate;

    @JsonProperty ("enddate")
    private Timestamp enddate;

    @JsonProperty ("description")
    private String description;
    
}


enum Worktype {
    inPlace,
    hybrid,
    Telecommuting
}

enum Jobtype {
    fulltime,
    parttime,
    self_Employment,
    freelnce,
    contracting,
    internship,
    paidintern,
    seasonal,
    voluntary
}