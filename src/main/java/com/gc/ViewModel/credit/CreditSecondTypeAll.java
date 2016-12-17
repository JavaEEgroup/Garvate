package com.gc.ViewModel.credit;


import com.gc.model.CreditSecondType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreditSecondTypeAll {
    private int status;
    private List<ModelWithIdDesc> results;

    public CreditSecondTypeAll() {

    }
    public CreditSecondTypeAll(int status){
        this();
        this.status = status;
    }

    public CreditSecondTypeAll(int status, List<CreditSecondType> creditSecondTypes) {
        this(status);
        this.results = new ArrayList<>();
        results.addAll(creditSecondTypes.stream().map(ModelWithIdDesc::new).collect(Collectors.toList()));
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

