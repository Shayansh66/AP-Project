package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;



public class JobPosition {

    @JsonProperty ("id")
    private int id;

    @JsonProperty ("userid")
    private int userid;

    @JsonProperty ("title")
    private String title;

    @JsonProperty ("companyName")
    private String companyName;

    @JsonProperty ("worktype")
    private String worktype;

    @JsonProperty ("jobType")
    private String jobType;

    @JsonProperty ("profession")
    private String profession;

    @JsonProperty ("description")
    private String description;


    // constructors
    public JobPosition(int id, int userid, String title, String companyName, String worktype, String jobType,
            String profession, String description) {
        this.id = id;
        this.userid = userid;
        this.title = title;
        this.companyName = companyName;
        this.worktype = worktype;
        this.jobType = jobType;
        this.profession = profession;
        this.description = description;
    }

    public JobPosition() {
        
    }

    
    // accessor and mutators
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWorkType() {
        return worktype;
    }

    public void setWorkType(String worktype) {
        this.worktype = worktype;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "JobPosition [id=" + id + ", userid=" + userid + ", title=" + title + ", companyName="
                + companyName + ", worktype=" + worktype + ", jobType=" + jobType + ", profession=" + profession
                + ", description=" + description + "]";
    }

}
