package com.gc.ViewModel.project;


public class ProjectStatus {

    private Long id;
    private String description;

    public ProjectStatus() {

    }

    public ProjectStatus(com.gc.model.ProjectStatus status) {
        this.id = status.getId();
        this.description = status.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
