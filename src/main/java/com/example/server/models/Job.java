package main.java.com.example.server.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

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
    private Date startdate;

    @JsonProperty ("enddate")
    private Date enddate;

    @JsonProperty ("description")
    private String description;

    







    public enum Worktype {
        inPlace,
        hybrid,
        Telecommuting
    }

    public enum Jobtype {
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



  public static void main(String[] args) {
    
  }  
}
