package com.gc.ViewModel.credit;


import com.gc.model.CreditFirstType;
import com.gc.model.CreditSecondType;
import com.gc.model.CreditStatus;
import com.gc.model.CreditThirdType;

public class ModelWithIdDesc {
    private Long id;
    private String description;

    public ModelWithIdDesc() {

    }

    public ModelWithIdDesc(CreditStatus status) {
        this.id = status.getId();
        this.description = status.getDescription();
    }

    public ModelWithIdDesc(CreditFirstType firstType) {
        this.id = firstType.getId();
        this.description = firstType.getDescription();
    }
    public ModelWithIdDesc(CreditSecondType firstType) {
        this.id = firstType.getId();
        this.description = firstType.getDescription();
    }
    public ModelWithIdDesc(CreditThirdType firstType) {
        this.id = firstType.getId();
        this.description = firstType.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
