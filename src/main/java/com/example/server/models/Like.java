package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Like {
    @JsonProperty ("liker")
    private String liker;

    @JsonProperty ("liked")
    private String liked;

    public String getLiker() {
        return liker;
    }

    public void setLiker(String liker) {
        this.liker = liker;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public Like(String liker, String liked) {
        this.liker = liker;
        this.liked = liked;
    }

    public Like() {
    }

    @Override
    public String toString() {
        return "Like [liker=" + liker + ", liked=" + liked + "]";
    }


    
}
