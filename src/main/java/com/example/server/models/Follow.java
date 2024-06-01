package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Follow {

    @JsonProperty ("followerid")
    private int followerid;

    @JsonProperty ("followingid")
    private int followingid;

    @JsonProperty ("isConnection")
    private boolean isConnection;

    
    // constructors
    public Follow(int followerid, int followingid, boolean isConnection) {
        this.followerid = followerid;
        this.followingid = followingid;
        this.isConnection = isConnection;
    }

    public Follow() {
    }


    // accessor and mutators
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

    public boolean isConnection() {
        return isConnection;
    }

    public void setConnection(boolean isConnection) {
        this.isConnection = isConnection;
    }

}
