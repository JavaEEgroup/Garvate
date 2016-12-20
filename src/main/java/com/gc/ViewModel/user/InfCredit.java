package com.gc.ViewModel.user;


import com.gc.ViewModel.credit.CreditInf;
import com.gc.model.Credit;
import com.gc.model.User;

import java.util.ArrayList;
import java.util.List;

public class InfCredit {

    private int status;
    private UserBriefResults user;
    private List<CreditInf> results;

    public InfCredit() {
    }

    public InfCredit(int status) {
        this.status = status;
    }

    public InfCredit(int status, User user, List<Credit> results) {
        this.status = status;
        this.user = new UserBriefResults(user);
        if(results != null) {
            this.results = new ArrayList<>();
            for(Credit credit : results) {
                this.results.add(new CreditInf(credit));
            }
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserBriefResults getUser() {
        return user;
    }

    public void setUser(UserBriefResults user) {
        this.user = user;
    }

    public List<CreditInf> getResults() {
        return results;
    }

    public void setResults(List<CreditInf> results) {
        this.results = results;
    }
}
