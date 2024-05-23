package models;
import com.fasterxml.jackson.annotation.jsonproperty;


public class Skill {
    
    @jsonproperty ("id")
    private String id;

    @jsonproperty ("explaintion")
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