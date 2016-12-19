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
    String phone;
    String email;

    public UserBriefResults(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.email = user.getEmail();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
