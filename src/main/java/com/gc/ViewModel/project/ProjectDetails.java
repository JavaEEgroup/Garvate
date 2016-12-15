package com.gc.ViewModel.project;


import com.gc.model.Project;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectDetails {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int NO_AUTHORITY = 2;
    public static final int PROJECT_NOT_FOUND = 3;
    public static final int NOT_MODIFIABLE = 4;

    private int status;
    private Long id;
    private Long user_id;
    private String username;
    private String name;
    private String description;
    private String note;
    private String start_time;
    private String end_time;
    private ProjectStatus project_status;
    private ProjectType project_type;
    private List<Fund> funds;
    private List<ProjectDocument> documents;

    public ProjectDetails() {
    }

    public ProjectDetails(int status) {
        this.status = status;
    }

    public ProjectDetails(int status, Project project) {
        this.status = status;
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.note = project.getNote();
        this.start_time = project.getStart_time().toString();
        Timestamp end_time = project.getEnd_time();
        if(end_time != null) this.end_time = end_time.toString();
        this.project_status = new ProjectStatus(project.getProject_status());
        this.project_type = new ProjectType(project.getProject_type());
        this.funds = new ArrayList<>();
        funds.addAll(project.getFundList().stream().map(Fund::new).collect(Collectors.toList()));
        this.documents = new ArrayList<>();
        documents.addAll(project.getProjectDocumentList().stream().map(ProjectDocument::new).collect(Collectors.toList()));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
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

    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }

    public List<ProjectDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ProjectDocument> documents) {
        this.documents = documents;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
