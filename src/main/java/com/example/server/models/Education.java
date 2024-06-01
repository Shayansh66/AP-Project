package main.java.com.example.server.models;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Education {

    @JsonProperty("id")
    private int id;

    @JsonProperty ("userid")
    private int userid;

    @JsonProperty("institutionName")
    private String institutionName;

    @JsonProperty("studyField")
    private String studyField;

    @JsonProperty("startDate")
    private Timestamp startDate;

    @JsonProperty("endDate")
    private Timestamp endDate;

    @JsonProperty("grade")
    private float grade;

    @JsonProperty("descriptionOfActivities")
    private String descriptionOfActivities;

    @JsonProperty("description")
    private String description;

    @JsonProperty("notifyChanges")
    private boolean notifyChanges;


    // constructors
    public Education(int id, int userid, String institutionName, String studyField, Timestamp startDate, Timestamp endDate,
    float grade, String descriptionOfActivities, String description, boolean notifyChanges) {
        this.id = id;
        this.userid = userid;
        this.institutionName = institutionName;
        this.studyField = studyField;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
        this.descriptionOfActivities = descriptionOfActivities;
        this.description = description;
        this.notifyChanges = notifyChanges;
    }
    
    public Education() {
        
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int userid) {
        this.userid = userid;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getStudyField() {
        return studyField;
    }

    public void setStudyField(String studyField) {
        this.studyField = studyField;
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

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getDescriptionOfActivities() {
        return descriptionOfActivities;
    }

    public void setDescriptionOfActivities(String descriptionOfActivities) {
        this.descriptionOfActivities = descriptionOfActivities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNotifyChanges() {
        return notifyChanges;
    }

    public void setNotifyChanges(boolean notifyChanges) {
        this.notifyChanges = notifyChanges;
    }

    
    @Override
    public String toString() {
        return "Education [id=" + id + ", institutionName=" + institutionName + ", studyField=" + studyField
                + ", startDate=" + startDate + ", endDate=" + endDate + ", grade=" + grade
                + ", descriptionOfActivities=" + descriptionOfActivities + ", description=" + description + ", notifyChanges=" + notifyChanges + "]";
    }
    
}
