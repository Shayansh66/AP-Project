package main.java.com.example.server.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    @JsonProperty ("id")
    private String id;


    @JsonProperty ("country")
    private String country;

    @JsonProperty ("city")
    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Location(String id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public Location() {
    }

    @Override
    public String toString() {
        return "Location [id=" + id + ", country=" + country + ", city=" + city + "]";
    }

    



     
}