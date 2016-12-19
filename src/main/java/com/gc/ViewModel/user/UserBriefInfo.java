package com.gc.ViewModel.user;


import com.gc.model.User;

public class UserBriefInfo {
    private int status;
    private UserBriefResults results;

    public UserBriefInfo() {

    }

    public UserBriefInfo(int status) {
        this.status = status;
    }

    public UserBriefInfo(int status, User user) {
        this(status);
        this.results = new UserBriefResults(user);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserBriefResults getResults() {
        return results;
    }

    public void setResults(UserBriefResults results) {
        this.results = results;
    }
}


