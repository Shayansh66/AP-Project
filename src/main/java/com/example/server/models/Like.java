package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Like {

    @JsonProperty ("id")
    private int id;
    
    @JsonProperty ("liker")
    private int likerid;

    @JsonProperty ("liked")
    private int likedPostid;

    
    public Like(int id, int likerid, int likedPostid) {
        this.id = id;
        this.likerid = likerid;
        this.likedPostid = likedPostid;
    }

    public Like() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikerid() {
        return likerid;
    }

    public void setLikerid(int likerid) {
        this.likerid = likerid;
    }

    public int getLikedPostid() {
        return likedPostid;
    }

    public void setLikedPostid(int likedPostid) {
        this.likedPostid = likedPostid;
    }


    @Override
    public String toString() {
        return "Like [id=" + id + ", likerid=" + likerid + ", likedPostid=" + likedPostid + "]";
    }

}
