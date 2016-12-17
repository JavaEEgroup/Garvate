package com.gc.ViewModel.credit;


import com.gc.model.CreditThirdType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreditThirdTypeAll {
    private int status;
    private List<ModelWithIdDesc> results;

    public CreditThirdTypeAll() {

    }
    public CreditThirdTypeAll(int status){
        this();
        this.status = status;
    }

    public CreditThirdTypeAll(int status, List<CreditThirdType> creditThirdTypes) {
        this(status);
        this.results = new ArrayList<>();
        results.addAll(creditThirdTypes.stream().map(ModelWithIdDesc::new).collect(Collectors.toList()));
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