package com.gc.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "fund")
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fund_type", nullable = false)
    private String fund_type;

    @Column(name = "edit_time")
    private Timestamp edit_time;

    @Column(name = "note")
    private String note;

    @Column(name = "value", nullable = false)
    private Long value;

    @JoinColumn(name = "project_id")
    @ManyToOne
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFund_type() {
        return fund_type;
    }

    public void setFund_type(String fund_type) {
        this.fund_type = fund_type;
    }

    public Timestamp getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(Timestamp edit_time) {
        this.edit_time = edit_time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
