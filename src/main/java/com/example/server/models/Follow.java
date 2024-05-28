package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Follow {
    @JsonProperty ("followers")
    private String followers;

    @JsonProperty ("following")
    private String following;

    @JsonProperty ("isConnection")
    private boolean isConnection;

    public Follow(String followers, String following, boolean isConnection) {
        this.followers = followers;
        this.following = following;
        this.isConnection = isConnection;
    }

    public Follow() {
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public boolean isConnection() {
        return isConnection;
    }

    public void setConnection(boolean isConnection) {
        this.isConnection = isConnection;
    }

    @Override
    public String toString() {
        return "Follow [followers=" + followers + ", following=" + following + ", isConnection=" + isConnection + "]";
    }

    

  

    
    
}
