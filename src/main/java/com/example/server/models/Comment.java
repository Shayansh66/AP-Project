package main.java.com.example.server.models;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Comment extends Post {
    
    @JsonProperty ("relatedPost")
    private int relatedPostid;


    // constructors
    public Comment(int id, int writterid, String content, int likeNumber, int commentNumber, Timestamp createDate,int relatedPostId) {
        super(id, writterid, content, likeNumber, commentNumber, createDate);
        this.relatedPostid = relatedPostId;
    }

    public Comment() {
    }


    // accessor and mutators
    public int getRelatedPostid() {
        return relatedPostid;
    }

    public void setRelatedPostid(int relatedPost) {
        this.relatedPostid = relatedPost;
    }
    

    @Override
    public String toString() {
        return "Comment [relatedPostid=" + relatedPostid + "]";
    }
    
}
