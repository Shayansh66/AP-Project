package main.java.com.example.server.models;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Message {
    
    @JsonProperty ("senderid")
    private String senderid;

    @JsonProperty ("recieverid")
    private String recieverid;

    @JsonProperty ("context")
    private String context;

    @JsonProperty ("multiMedia")
    private String[] multiMedia;

    
    public Message(String senderid, String recieverid, String context, String[] multiMedia) {
        this.senderid = senderid;
        this.recieverid = recieverid;
        this.context = context;
        this.multiMedia = multiMedia;
    }

    public Message() {
    }

    
    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getRecieverid() {
        return recieverid;
    }

    public void setRecieverid(String recieverid) {
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

    @Override
    public String toString() {
        return "Message [senderid=" + senderid + ", recieverid=" + recieverid + ", context=" + context + ", multiMedia="
                + Arrays.toString(multiMedia) + "]";
    }
    
}
