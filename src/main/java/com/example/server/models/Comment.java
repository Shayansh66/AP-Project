package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public class Comment extends Post {
    
    @JsonProperty ("relatedPost")
    private String relatedPostid;


    // constructors
    public Comment(String id, String writterid, String content, int likeNumber, int commentNumber, Timestamp createDate,
            String relatedPost) {
        super(id, writterid, content, likeNumber, commentNumber, createDate);
        this.relatedPostid = relatedPost;
    }

    public Comment() {
    }


    // accessor and mutators
    public String getRelatedPostid() {
        return relatedPostid;
    }

    public void setRelatedPostid(String relatedPost) {
        this.relatedPostid = relatedPost;
    }

    @Override
    public String toString() {
        return "Comment [relatedPostid=" + relatedPostid + "]";
    }
    
}
