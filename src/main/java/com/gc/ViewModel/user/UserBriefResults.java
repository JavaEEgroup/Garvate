package com.gc.ViewModel.user;

import com.gc.model.User;

public class UserBriefResults {
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