package com.gc.ViewModel.project;

public class Fund {

    private Long id;
    private String fund_type;
    private String note;
    private Long value;
    private String edit_time;

    public Fund(){

    }

    public Fund(com.gc.model.Fund fund) {
        this.id = fund.getId();
        this.fund_type = fund.getFund_type();
        this.note = fund.getNote();
        this.value = fund.getValue();
        this.edit_time = fund.getEdit_time().toString();
    }

    public String getFund_type() {
        return fund_type;
    }

    public void setFund_type(String fund_type) {
        this.fund_type = fund_type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
