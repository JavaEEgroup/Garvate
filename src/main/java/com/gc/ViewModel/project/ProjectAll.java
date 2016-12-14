package com.gc.ViewModel.project;

import com.gc.model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectAll {

    private int status;
    private List<ProjectInfo> projects;

    public ProjectAll() {

    }

    public ProjectAll(int status) {
        this.status = status;
    }

    public void add2ProjectList(List<Project> projects) {
        this.projects = new ArrayList<>();
        this.projects.addAll(projects.stream().map(ProjectInfo::new).collect(Collectors.toList()));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ProjectInfo> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectInfo> projects) {
        this.projects = projects;
    }
}

class ProjectInfo {

    private Long id;
    private String name;
    private ProjectStatus project_status;
    private ProjectType project_type;

    public ProjectInfo() {

    }

    public ProjectInfo(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.project_status = new ProjectStatus(project.getProject_status());
        this.project_type = new ProjectType(project.getProject_type());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectStatus getProject_status() {
        return project_status;
    }

    public void setProject_status(ProjectStatus project_status) {
        this.project_status = project_status;
    }

    public ProjectType getProject_type() {
        return project_type;
    }

    public void setProject_type(ProjectType project_type) {
        this.project_type = project_type;
    }
}