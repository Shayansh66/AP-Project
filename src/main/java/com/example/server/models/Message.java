package main.java.com.example.server.models;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Message {
    
    @JsonProperty ("id")
    private int id;

    @JsonProperty ("senderid")
    private int senderid;

    @JsonProperty ("recieverid")
    private int recieverid;

    @JsonProperty ("context")
    private String context;

    @JsonProperty ("multiMedia")
    private String[] multiMedia;

    
    public Message(int id, int senderid, int recieverid, String context, String[] multiMedia) {
        this.id = id;
        this.senderid = senderid;
        this.recieverid = recieverid;
        this.context = context;
        this.multiMedia = multiMedia;
    }

    public Message() {
    }

    
    public int getSenderid() {
        return senderid;
    }

    public void setSenderid(int senderid) {
        this.senderid = senderid;
    }

    public int getRecieverid() {
        return recieverid;
    }

    public void setRecieverid(int recieverid) {
        this.recieverid = recieverid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String[] getMultiMedia() {
        return multiMedia;
    }

    public void setMultiMedia(String[] multiMedia) {
        this.multiMedia = multiMedia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Message [id=" + id + ", senderid=" + senderid + ", recieverid=" + recieverid + ", context=" + context
                + ", multiMedia=" + Arrays.toString(multiMedia) + "]";
    }
    
}
