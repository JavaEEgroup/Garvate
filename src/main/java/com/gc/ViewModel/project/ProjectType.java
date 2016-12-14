package com.gc.ViewModel.project;

/**
 * Created by jx-pc on 2016/12/14.
 */
public class ProjectType {

    private Long id;
    private String description;

    public ProjectType() {

    }

    public ProjectType(com.gc.model.ProjectType type) {
        this.id = type.getId();
        this.description = type.getDescription();
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
