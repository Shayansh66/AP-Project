package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Skill {
    
    @JsonProperty ("id")
    private int id;

    @JsonProperty ("explaintion")
    private String explaination;

    @JsonProperty ("userid")
    private int userid;
    
   

    
    public Skill(int id, String explaination, int userid) {
        this.id = id;
        this.explaination = explaination;
        this.userid = userid;
    }

    

    public Skill() {
    }



    // accessor and mutators
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    
   

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }



    @Override
    public String toString() {
        return "Skill [id=" + id + ", explaination=" + explaination + ", userid=" + userid + "]";
    }

    
    
}