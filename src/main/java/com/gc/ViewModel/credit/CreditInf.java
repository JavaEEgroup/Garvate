package com.gc.ViewModel.credit;

import com.gc.model.Credit;

public class CreditInf {
    private Long id;
    private String name;
    private String create_credit_time;
    private String get_prize_time;
    private int grade = 0;
    private int value = 0;
    private String credit_status;
    private String credit_first_type;
    private String credit_second_type;
    private String credit_third_type;

    public CreditInf() {

    }

    public CreditInf(Credit credit) {
        this.id = credit.getId();
        this.name = credit.getName();
        this.create_credit_time = credit.getCreateCreditTime().toString();
        this.get_prize_time = credit.getGetPrizeTime().toString();
        this.grade = credit.getGrade();
        this.value = credit.getValue();
        this.credit_status = credit.getCreditStatus().getDescription();
        this.credit_first_type = credit.getCreditFirstType().getDescription();
        this.credit_second_type = credit.getCreditSecondType().getDescription();
        this.credit_third_type = credit.getCreditThirdType().getDescription();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_credit_time() {
        return create_credit_time;
    }

    public void setCreate_credit_time(String create_credit_time) {
        this.create_credit_time = create_credit_time;
    }

    public String getGet_prize_time() {
        return get_prize_time;
    }

    public void setGet_prize_time(String get_prize_time) {
        this.get_prize_time = get_prize_time;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCredit_status() {
        return credit_status;
    }

    public void setCredit_status(String credit_status) {
        this.credit_status = credit_status;
    }

    public String getCredit_first_type() {
        return credit_first_type;
    }

    public void setCredit_first_type(String credit_first_type) {
        this.credit_first_type = credit_first_type;
    }

    public String getCredit_second_type() {
        return credit_second_type;
    }

    public void setCredit_second_type(String credit_second_type) {
        this.credit_second_type = credit_second_type;
    }

    public String getCredit_third_type() {
        return credit_third_type;
    }

    public void setCredit_third_type(String credit_third_type) {
        this.credit_third_type = credit_third_type;
    }

}
