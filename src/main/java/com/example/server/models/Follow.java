package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Follow {

    @JsonProperty ("id")
    private int id;

    @JsonProperty ("followerid")
    private int followerid;

    @JsonProperty ("followingid")
    private int followingid;


    
    // constructors
    public Follow(int id, int followerid, int followingid) {
        this.id = id;
        this.followerid = followerid;
        this.followingid = followingid;

    }

    public Follow() {
    }


    // accessor and mutators
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowerid() {
        return followerid;
    }

    public void setFollowerid(int followerid) {
        this.followerid = followerid;
    }

    public int getFollowingid() {
        return followingid;
    }

    public void setFollowingid(int followingid) {
        this.followingid = followingid;
    }

    @Override
    public String toString() {
        return "Follow [id=" + id + ", followerid=" + followerid + ", followingid=" + followingid + "]";
    }
     

}
