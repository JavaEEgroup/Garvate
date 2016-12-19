package com.gc.ViewModel.user;

import com.gc.model.User;

public class UserBriefResults {
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