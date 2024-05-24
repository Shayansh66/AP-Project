package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class Post {

    @JsonProperty ("id")
    private String id;

    @JsonProperty ("writterid")
    private String writterid;

    @JsonProperty ("content")
    private String content;

    @JsonProperty ("lineNumber")
    private int lineNumber;

    @JsonProperty ("commentNumber")
    private int commentNumber;

    @JsonProperty ("createDate")
    private Date createDate;

    // constructors
    public Post(String id, String writterid, String content, int lineNumber, int commentNumber, Date createDate) {
        this.id = id;
        this.writterid = writterid;
        this.content = content;
        this.lineNumber = lineNumber;
        this.commentNumber = commentNumber;
        this.createDate = createDate;
    }

    public Post() {
    }


    // getter and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWritterid() {
        return writterid;
    }

    public void setWritterid(String writterid) {
        this.writterid = writterid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
    @Override
    public String toString() {
        return  "Post [id=" + id +
                ", writterid=" + writterid +
                ", content=" + content +
                ", lineNumber=" + lineNumber +
                ", commentNumber=" + commentNumber +
                ", createDate=" + createDate +
                "]";
    }

    

}
