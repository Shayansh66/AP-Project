 package main.java.com.example.server.models;

import java.time.LocalDate;

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
    private LocalDate startDate;

    @JsonProperty ("endDate")
    private LocalDate endDate;
    
    
    @JsonProperty ("stillWorking")
    private boolean stillWorking;
    


    @Override
    public String toString() {
        return "ParticipatedOrginazation [id=" + id + ", userId=" + userId + ", institution=" + institution
                + ", position=" + position + ", startDate=" + startDate + ", endDate=" + endDate + ", stillWorking="
                + stillWorking + "]";
    }


    public ParticipatedOrginazation() {
    }


    public ParticipatedOrginazation(int id, int userId, String institution, String position, LocalDate startDate,
            LocalDate endDate, boolean stillWorking) {
        this.id = id;
        this.userId = userId;
        this.institution = institution;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stillWorking = stillWorking;
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


    public LocalDate getStartDate() {
        return startDate;
    }


    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    public boolean isStillWorking() {
        return stillWorking;
    }


    public void setStillWorking(boolean stillWorking) {
        this.stillWorking = stillWorking;
    }


}