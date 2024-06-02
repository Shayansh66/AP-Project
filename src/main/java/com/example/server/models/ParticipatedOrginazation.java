 package main.java.com.example.server.models;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ParticipatedOrginazation {

    @JsonProperty ("id")
    private int id;

    @JsonProperty ("userId")
    private int userId;
    
    @JsonProperty ("institution")
    private String institution;

    @JsonProperty ("position")
    private String position;

    @JsonProperty ("startDate")
    private Timestamp startDate;

    @JsonProperty ("endDate")
    private Timestamp endDate;
    
    @JsonProperty ("stillWorking")
    private boolean stillWorking;
    
    
    // constructors
    public ParticipatedOrginazation(int id, int userId, String institution, String position, Timestamp startDate,
            Timestamp endDate, boolean stillWorking) {
        this.id = id;
        this.userId = userId;
        this.institution = institution;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stillWorking = stillWorking;
    }

    public ParticipatedOrginazation() {
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
    
    public String getInstitution() {
        return institution;
    }
    
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Timestamp getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
    
    public Timestamp getEndDate() {
        return endDate;
    }
     
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
    
    public boolean isStillWorking() {
        return stillWorking;
    }
    
    public void setStillWorking(boolean stillWorking) {
        this.stillWorking = stillWorking;
    }
    
    
    @Override
    public String toString() {
        return "ParticipatedOrginazation [id=" + id + ", userId=" + userId + ", institution=" + institution
                + ", position=" + position + ", startDate=" + startDate + ", endDate=" + endDate + ", stillWorking="
                + stillWorking + "]";
    }
    
}