package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Skill {
    
    @JsonProperty ("id")
    private int id;

    @JsonProperty ("explaintion")
    private String explaination;

    
    // constructors
    public Skill(int id, String explaination) {
        this.id = id;
        this.explaination = explaination;
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

    
    @Override
    public String toString() {
        return "Skill [id=" + id + ", explaination=" + explaination + "]";
    }
    
}