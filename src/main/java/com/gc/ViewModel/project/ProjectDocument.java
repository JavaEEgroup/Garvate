package com.gc.ViewModel.project;

/**
 * Created by jx-pc on 2016/12/14.
 */
public class ProjectDocument {

    private Long id;
    private String filename;
    private String filepath;
    private String type;
    private String upload_time;

    public ProjectDocument() {

    }

    public ProjectDocument(com.gc.model.ProjectDocument document) {
        this.id = document.getId();
        this.filename = document.getFilename();
        this.filepath = document.getFilepath();
        this.type = document.getTypeName();
        this.upload_time = document.getUpload_time().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }
}
