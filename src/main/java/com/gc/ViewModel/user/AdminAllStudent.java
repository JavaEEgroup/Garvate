package com.gc.ViewModel.user;


import com.gc.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdminAllStudent {

    private int status;
    private List<UserBriefResults> results;

    public AdminAllStudent() {
    }

    public AdminAllStudent(int status) {
        this.status = status;
    }

    public AdminAllStudent(int status, List<User> results) {
        this.status = status;
        if(results != null) {
            this.results = new ArrayList<>();
            for(User user : results) {
                if(user.getRoleList().get(0).getDesc().equals("student")) {
                    this.results.add(new UserBriefResults(user));
                }
            }
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
