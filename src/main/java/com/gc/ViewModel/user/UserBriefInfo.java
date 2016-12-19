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

class UserBriefResults {
    Long id;
    String username;

    public UserBriefResults(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
