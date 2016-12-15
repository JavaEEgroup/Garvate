package com.gc.ViewModel.credit;


import com.gc.ViewModel.Entry;

public class CreditBriefInf extends Entry{
    Long credit_id;

    public CreditBriefInf(){
        super();
    }
    public CreditBriefInf(int state) {
        super(state);
    }

    public CreditBriefInf(int state, Long credit_id) {
        this(state);
        this.credit_id = credit_id;
    }
}
