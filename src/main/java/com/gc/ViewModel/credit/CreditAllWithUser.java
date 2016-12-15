package com.gc.ViewModel.credit;


import com.gc.model.Credit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreditAllWithUser {
    private int status;
    private List<CreditInfWithUser> results;

    public CreditAllWithUser(){}

    public CreditAllWithUser(int status) {
        this();
        this.status = status;
    }

    public CreditAllWithUser(int status, List<Credit> credits) {
        this(status);

        results = new ArrayList<>();
        results.addAll(credits.stream().map(CreditInfWithUser::new).collect(Collectors.toList()));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CreditInfWithUser> getResults() {
        return results;
    }

    public void setResults(List<CreditInfWithUser> results) {
        this.results = results;
    }
}