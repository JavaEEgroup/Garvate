package com.gc.ViewModel.project;


public class ProjectAdd {

    private int status;
    private Long id;

    public ProjectAdd() {

    }

    public ProjectAdd(int status) {
        this.status = status;
    }

    public ProjectAdd(int status, Long id) {
        this.status = status;
        this.id = id;
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
}
