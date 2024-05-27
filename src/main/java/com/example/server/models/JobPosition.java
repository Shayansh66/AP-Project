package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import main.java.com.example.server.models.Job.Jobtype;
import main.java.com.example.server.models.Job.Worktype;


public class JobPosition {

    @JsonProperty ("id")
    private int id;

    @JsonProperty ("creatorId")
    private int creatorId;

    @JsonProperty ("title")
    private String title;

    @JsonProperty ("companyName")
    private String companyName;

    @JsonProperty ("worktype")
    private Worktype worktpye;

    @JsonProperty ("jobType")
    private Jobtype jobType;

    @JsonProperty ("profession")
    private String profession;

    @JsonProperty ("descreption")
    private String descreption;


    // constructors
    public JobPosition(int id, int creatorId, String title, String companyName, Worktype worktpye, Jobtype jobType,
            String profession, String descreption) {
        this.id = id;
        this.creatorId = creatorId;
        this.title = title;
        this.companyName = companyName;
        this.worktpye = worktpye;
        this.jobType = jobType;
        this.profession = profession;
        this.descreption = descreption;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
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

    public Worktype getWorktpye() {
        return worktpye;
    }

    public void setWorktpye(Worktype worktpye) {
        this.worktpye = worktpye;
    }

    public Jobtype getJobType() {
        return jobType;
    }

    public void setJobType(Jobtype jobType) {
        this.jobType = jobType;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }


    @Override
    public String toString() {
        return "JobPosition [id=" + id + ", creatorId=" + creatorId + ", title=" + title + ", companyName="
                + companyName + ", worktpye=" + worktpye + ", jobType=" + jobType + ", profession=" + profession
                + ", descreption=" + descreption + "]";
    }

}
