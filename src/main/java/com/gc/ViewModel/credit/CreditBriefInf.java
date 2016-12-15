package com.gc.ViewModel.credit;


import com.gc.ViewModel.Entry;

public class CreditBriefInf {
    private int status;
    private Long credit_id;

    public CreditBriefInf(){
    }
    public CreditBriefInf(int status) {
        this();
        this.status = status;
    }

    public CreditBriefInf(int status, Long credit_id) {
        this(status);
        this.credit_id = credit_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(Long credit_id) {
        this.credit_id = credit_id;
    }
}
