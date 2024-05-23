package models;
import com.fasterxml.jackson.annotation.jsonproperty;
import java.util.Date;

public class Job {  
    @jsonproperty ("id")
    private String id;

    @jsonproperty ("title")
    private String title;
    
    @jsonproperty ("companyname")
    private String companyname;

    @jsonproperty ("workplace")
    private String workplace;

    @jsonproperty ("isworking")
    private boolean isworking;

    @jsonproperty ("worktype")
    private Worktype worktype;

    @jsonproperty ("jobtype")
    private Jobtype jobtype;

    @jsonproperty ("startdate")
    private Date startdate;

    @jsonproperty ("enddate")
    private Date enddate;

    @jsonproperty ("description")
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
