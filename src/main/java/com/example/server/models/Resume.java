package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Resume {
    @JsonProperty ("ownerId")
    private int ownerId;

    @JsonProperty ("positionId")
    private String positionId;

    @JsonProperty ("resumeFile")
    private String  resumeFile;

    @JsonProperty ("descreption")
    private String descreption;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(String resumeFile) {
        this.resumeFile = resumeFile;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public Resume(int ownerId, String positionId, String resumeFile, String descreption) {
        this.ownerId = ownerId;
        this.positionId = positionId;
        this.resumeFile = resumeFile;
        this.descreption = descreption;
    }

    public Resume() {
    }

    @Override
    public String toString() {
        return "Resume [ownerId=" + ownerId + ", positionId=" + positionId + ", resumeFile=" + resumeFile
                + ", descreption=" + descreption + "]";
    }

    
    
    
}
