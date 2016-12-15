package com.gc.ViewModel.project;


public class ProjectDelete {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int NO_AUTHORITY = 2;
    public static final int PROJECT_NOT_FOUND = 3;
    public static final int NOT_MODIFIABLE = 4;

    private int status;

    public ProjectDelete() {
    }

    public ProjectDelete(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
