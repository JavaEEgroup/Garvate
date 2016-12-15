package com.gc.ViewModel.credit;


import com.gc.model.Credit;

public class CreditInfWithUser extends CreditInf{
    private String username;
    private Long user_id;

    public CreditInfWithUser() {

    }
    public CreditInfWithUser(Credit credit){
        super(credit);
        this.username = credit.getUser().getUsername();
        this.user_id = credit.getUser().getId();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
