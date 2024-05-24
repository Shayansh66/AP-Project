package main.java.com.example.server.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    @JsonProperty ("id")
    private String id;


    @JsonProperty ("country")
    private String country;

    @JsonProperty ("city")
    private String city;




     
}