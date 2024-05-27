package main.java.com.example.server.models;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Post {

    @JsonProperty ("id")
    private int id;

    @JsonProperty ("writterid")
    private int writterid;

    @JsonProperty ("content")
    private String content;

    @JsonProperty ("liKeNumber")
    private int liKeNumber;

    @JsonProperty ("commentNumber")
    private int commentNumber;

    @JsonProperty ("createDate")
    private Timestamp createDate;


    // constructors
    public Post(int id, int writterid, String content, int likeNumber, int commentNumber, Timestamp createDate) {
        this.id = id;
        this.writterid = writterid;
        this.content = content;
        this.liKeNumber = likeNumber;
        this.commentNumber = commentNumber;
        this.createDate = createDate;
    }

    public Post() {
    }


    // accessor and mutators
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWritterid() {
        return writterid;
    }

    public void setWritterid(int writterid) {
        this.writterid = writterid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLiKeNumber() {
        return liKeNumber;
    }

    public void setLiKeNumber(int likeNumber) {
        this.liKeNumber = likeNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    
    @Override
    public String toString() {
        return  "Post [id=" + id +
                ", writterid=" + writterid +
                ", content=" + content +
                ", likeNumber=" + liKeNumber +
                ", commentNumber=" + commentNumber +
                ", createDate=" + createDate +
                "]";
    }

}
