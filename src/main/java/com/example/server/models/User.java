package models;
import com.fasterxml.jackson.annotation.jsonproperty;












public class User {
    @jsonproperty ("id")
    private String id;

    @jsonproperty ("name")
    private String  name;

    @jsonproperty ("lastname")
    private String lastname;

    @jsonproperty ("additinolname")
    private String additionalname;

    @jsonproperty ("headtitle")
    private String  headtitle;

    





    public static void main(String[] args) {

    }
}