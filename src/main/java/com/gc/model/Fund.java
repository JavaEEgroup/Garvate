package com.gc.model;

import com.gc.Utils.Utils;

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

    public Fund() {
    }

    public Fund(String fund_type, String note, Long value, Project project) {
        this.fund_type = fund_type;
        this.note = note;
        this.value = value;
        this.project = project;
        this.edit_time = Utils.getCurrentTime();
    }

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
