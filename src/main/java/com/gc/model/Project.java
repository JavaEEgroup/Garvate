package com.gc.model;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "description")
    private String description;


    @Column(name = "start_time")
    private Timestamp start_time;


    @Column(name = "end_time")
    private Timestamp end_time;


    @Column(name = "note")
    private String note;

    @JoinColumn(name = "project_status_id")
    @ManyToOne
    private ProjectStatus project_status;

    @JoinColumn(name = "project_type_id")
    @ManyToOne
    private ProjectType project_type;

    @JoinColumn(name = "project_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProjectDocument> projectDocumentList;

    @JoinColumn(name = "project_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Fund> fundList;

    @OneToOne(mappedBy = "project")
    private Team team;

    public Project() {

    }

    public Project(String name, String description, String note, ProjectType project_type, ProjectStatus project_status, Timestamp start_time) {

        this.name = name;
        this.description = description;
        this.note = note;
        this.project_type = project_type;
        this.project_status = project_status;
        this.start_time = start_time;
    }

    public boolean hasUser(User user) {
        return team.hasUser(user);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public List<ProjectDocument> getProjectDocumentList() {
        return projectDocumentList;
    }

    public void setProjectDocumentList(List<ProjectDocument> projectDocumentList) {
        this.projectDocumentList = projectDocumentList;
    }

    public List<Fund> getFundList() {
        return fundList;
    }

    public void setFundList(List<Fund> fundList) {
        this.fundList = fundList;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
