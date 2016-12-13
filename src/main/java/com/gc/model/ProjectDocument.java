package com.gc.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "project_document")
public class ProjectDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "type", nullable = false)
    private Long type;

    @Column(name = "upload_time")
    private Timestamp upload_time;

    @Column(name = "filepath", nullable = false)
    private String filepath;

    @JoinColumn(name = "project_id")
    @ManyToOne
    private Project project;

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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Timestamp getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Timestamp upload_time) {
        this.upload_time = upload_time;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
