package com.gc.ViewModel.project;


public class FundDelete {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int FUND_NOT_FOUND = 2;
    public static final int NO_AUTHORITY= 4;


    private int status;
    private Long id;

    public FundDelete() {
    }

    public FundDelete(int status) {
        this.status = status;
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
