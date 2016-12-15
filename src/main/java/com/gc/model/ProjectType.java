package com.gc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_type")
public class ProjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "open")
    private boolean open;

    @JoinColumn(name = "project_type_id")
    @OneToMany
    private List<Project> projectList;

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

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
