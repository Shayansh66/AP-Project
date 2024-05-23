package models;
import com.fasterxml.jackson.annotation.jsonproperty;

public class Location {
    @jsonproperty ("id")
    private String id;


    @jsonproperty ("country")
    private String country;

    @jsonproperty ("city")
    private String city;




     
}