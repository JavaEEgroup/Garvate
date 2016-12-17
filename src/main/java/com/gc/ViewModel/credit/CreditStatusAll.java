package com.gc.ViewModel.credit;


import com.gc.model.CreditStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreditStatusAll {
    private int status;
    private List<ModelWithIdDesc> results;

    public CreditStatusAll() {

    }
    public CreditStatusAll(int status){
        this();
        this.status = status;
    }

    public CreditStatusAll(int status, List<CreditStatus> creditStatuses) {
        this(status);
        this.results = new ArrayList<>();
        results.addAll(creditStatuses.stream().map(ModelWithIdDesc::new).collect(Collectors.toList()));
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
