package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Resume {
    @JsonProperty ("ownerId")
    private int ownerId;

    @JsonProperty ("positionId")
    private String positionId;

    @JsonProperty ("resumeFile")
    private String  resumeFile;

    @JsonProperty ("description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Resume(int ownerId, String positionId, String resumeFile, String description) {
        this.ownerId = ownerId;
        this.positionId = positionId;
        this.resumeFile = resumeFile;
        this.description = description;
    }

    public Resume() {
    }

    @Override
    public String toString() {
        return "Resume [ownerId=" + ownerId + ", positionId=" + positionId + ", resumeFile=" + resumeFile
                + ", description=" + description + "]";
    }

    
    
    
}
