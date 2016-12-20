package com.gc.ViewModel.user;

import com.gc.model.User;

public class UserBriefResults {
    Long id;
    String username;
    String phone;
    String email;
    String type;

    public UserBriefResults(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        if(user.getPhone() != null) this.phone = user.getPhone();
        if(user.getEmail() != null) this.email = user.getEmail();
//        if(user.getType() != null)this.type = user.getType().equals(2) ? "学生" : "管理员";

        if (!user.getRoleList().isEmpty()) {
                this.type = user.getRoleList().get(0).getDesc();
        }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}