package com.gc.ViewModel.user;


import com.gc.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserIds {
    private int status;
    private List<UserBriefResults> results;

    public UserIds() {

    }

    public UserIds(int status) {
        this.status = status;
    }

    public UserIds(int status, List<User> users) {
        this(status);
        this.results = new ArrayList<>();
        for (User user : users) {
            results.add(new UserBriefResults(user));
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<UserBriefResults> getResults() {
        return results;
    }

    public void setResults(List<UserBriefResults> results) {
        this.results = results;
    }
}

