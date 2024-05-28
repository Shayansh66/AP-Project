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
    
    @JsonProperty ("companyname")
    private String companyname;

    @JsonProperty ("workplace")
    private String workplace;

    @JsonProperty ("isworking")
    private boolean isworking;

    @JsonProperty ("worktype")
    private String worktype;

    @JsonProperty ("jobtype")
    private String jobtype;

    @JsonProperty ("startdate")
    private Timestamp startDate;

    @JsonProperty ("enddate")
    private Timestamp enddate;

    @JsonProperty ("description")
    private String description;

    @JsonProperty ("notifyChanges")
    private boolean notifyChanges;
    
    




    @Override
    public String toString() {
        return "Job [id=" + id + ", userid=" + userid + ", title=" + title + ", companyname=" + companyname
                + ", workplace=" + workplace + ", isworking=" + isworking + ", worktype=" + worktype + ", jobtype="
                + jobtype + ", startDate=" + startDate + ", enddate=" + enddate + ", description=" + description
                + ", notifyChanges=" + notifyChanges + "]";
    }

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
    public String getCompanyname() {
        return companyname;
    }
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
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
    public String getWorktype() {
        return worktype;
    }
    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }
    public String getJobtype() {
        return jobtype;
    }
    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
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

    
}


