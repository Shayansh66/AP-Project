package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Skill {
    
    @JsonProperty ("id")
    private String id;

    @JsonProperty ("explaintion")
    private String explaination;


    public String getExplaination() {
        return explaination;
    }
    public String getId() {
        return id;
    }
    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Skill (String id , String explaination)
    {
        this.id = id;
        this.explaination = explaination;

}
    
}