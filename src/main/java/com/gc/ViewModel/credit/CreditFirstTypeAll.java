package com.gc.ViewModel.credit;


import com.gc.model.CreditFirstType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreditFirstTypeAll {
    private int status;
    private List<ModelWithIdDesc> results;

    public CreditFirstTypeAll() {

    }
    public CreditFirstTypeAll(int status){
        this();
        this.status = status;
    }

    public CreditFirstTypeAll(int status, List<CreditFirstType> creditFirstTypes) {
        this(status);
        this.results = new ArrayList<>();
        for (CreditFirstType firstType : creditFirstTypes) {
            results.add(new ModelWithIdDesc(firstType));
        }
       // results.addAll(creditFirstTypes.stream().map(ModelWithIdDesc::new).collect(Collectors.toList()));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ModelWithIdDesc> getResults() {
        return results;
    }

    public void setResults(List<ModelWithIdDesc> results) {
        this.results = results;
    }
}
