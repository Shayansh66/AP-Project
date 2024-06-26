package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public class Job {

    @JsonProperty ("id")
    private int id;

    @JsonProperty ("userid")
    private int userid;

    @JsonProperty ("title")
    private String title;
    
    @JsonProperty ("companyName")
    private String companyName;

    @JsonProperty ("workplace")
    private String workplace;

    @JsonProperty ("isworking")
    private boolean isworking;

    @JsonProperty ("workType")
    private String workType;

    @JsonProperty ("jobType")
    private String jobType;

    @JsonProperty ("startdate")
    private Timestamp startDate;

    @JsonProperty ("enddate")
    private Timestamp enddate;

    @JsonProperty ("description")
    private String description;

    @JsonProperty ("notifyChanges")
    private boolean notifyChanges;
    
    enum WorkType {
        inPlace,
        hybrid,
        telecommuting
    }

    enum JobType {
        fullTime,
        partTime,
        selfEmployment,
        frelance,
        Contracting,
        internship,
        paidintern,
        seasonal,
        voluantary
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyname) {
        this.companyName = companyname;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public boolean isIsworking() {
        return isworking;
    }

    public void setIsworking(boolean isworking) {
        this.isworking = isworking;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String worktype) {
        this.workType = worktype;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobtype) {
        this.jobType = jobtype;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
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
    

    
    public Job(int id, int userid, String title, String companyName, String workplace, boolean isworking,
            String workType, String jobType, Timestamp startDate, Timestamp enddate, String description,
            boolean notifyChanges) {
        this.id = id;
        this.userid = userid;
        this.title = title;
        this.companyName = companyName;
        this.workplace = workplace;
        this.isworking = isworking;
        this.workType = workType;
        this.jobType = jobType;
        this.startDate = startDate;
        this.enddate = enddate;
        this.description = description;
        this.notifyChanges = notifyChanges;
    }
    

    public Job() {
    }

    @Override
    public String toString() {
        return "Job [id=" + id + ", userid=" + userid + ", title=" + title + ", companyName=" + companyName
                + ", workplace=" + workplace + ", isworking=" + isworking + ", workType=" + workType + ", jobType="
                + jobType + ", startDate=" + startDate + ", enddate=" + enddate + ", description=" + description
                + ", notifyChanges=" + notifyChanges + "]";
    }
    
}


