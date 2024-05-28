package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hashtag {
    
    @JsonProperty ("id")
    private String id;

    @JsonProperty ("content")
    private String content;

    public Hashtag(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public Hashtag() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Hashtag [id=" + id + ", content=" + content + "]";
    }

    
    
}
