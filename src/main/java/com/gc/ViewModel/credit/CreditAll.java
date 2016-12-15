package com.gc.ViewModel.credit;

import com.gc.model.Credit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreditAll {
    private int status;
    private List<CreditInf> results;

    public CreditAll(){}

    public CreditAll(int status) {
        this();
        this.status = status;
    }

    public CreditAll(int status, List<Credit> credits) {
        this(status);

        results = new ArrayList<>();
        results.addAll(credits.stream().map(CreditInf::new).collect(Collectors.toList()));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CreditInf> getResults() {
        return results;
    }

    public void setResults(List<CreditInf> results) {
        this.results = results;
    }
}


