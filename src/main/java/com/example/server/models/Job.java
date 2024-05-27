package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public class Job {

    @JsonProperty ("id")
    private int id;

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
    
    enum Worktype {
        inPlace,
        hybrid,
        Telecommuting
    }


    // constructors
    public Job(int id, String title, String companyname, String workplace, boolean isworking, Worktype worktype,
            Jobtype jobtype, Timestamp startDate, Timestamp enddate, String description) {
        this.id = id;
        this.title = title;
        this.companyname = companyname;
        this.workplace = workplace;
        this.isworking = isworking;
        this.worktype = worktype;
        this.jobtype = jobtype;
        this.startDate = startDate;
        this.enddate = enddate;
        this.description = description;
    }


    public Job() {

    }


    // accessor and mutators
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Worktype getWorktype() {
        return worktype;
    }

    public void setWorktype(Worktype worktype) {
        this.worktype = worktype;
    }

    public Jobtype getJobtype() {
        return jobtype;
    }

    public void setJobtype(Jobtype jobtype) {
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


    @Override
    public String toString() {
        return "Job [id=" + id + ", title=" + title + ", companyname=" + companyname + ", workplace=" + workplace
                + ", isworking=" + isworking + ", worktype=" + worktype + ", jobtype=" + jobtype + ", startDate="
                + startDate + ", enddate=" + enddate + ", description=" + description + "]";
    }
    
}


