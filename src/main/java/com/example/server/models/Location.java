package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Location {
    @JsonProperty ("id")
    private int id;

    @JsonProperty ("country")
    private String country;

    @JsonProperty ("city")
    private String city;
    
    
    // constructors
    public Location(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public Location() {

    }
    
    // accessor and mutators
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Location [id=" + id + ", country=" + country + ", city=" + city + "]";
    }

}