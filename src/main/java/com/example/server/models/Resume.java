package main.java.com.example.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Resume {

    @JsonProperty ("id")
    private int id;

    @JsonProperty ("ownerId")
    private int ownerId;

    @JsonProperty ("positionId")
    private int positionId;

    @JsonProperty ("resumeFile")
    private String resumeFile;

    @JsonProperty ("description")
    private String description;


    // constructors
    public Resume(int id, int ownerId, int positionId, String resumeFile, String description) {
        this.id = id;
        this.ownerId = ownerId;
        this.positionId = positionId;
        this.resumeFile = resumeFile;
        this.description = description;
    }

    public Resume() {

    }


    // accessor and mutators
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
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


    @Override
    public String toString() {
        return "Resume [id=" + id + ", ownerId=" + ownerId + ", positionId=" + positionId + ", resumeFile=" + resumeFile
                + ", description=" + description + "]";
    }
    
}
