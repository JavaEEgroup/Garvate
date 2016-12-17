package com.gc.controller;

import com.gc.ViewModel.credit.CreditFirstTypeAll;
import com.gc.ViewModel.credit.CreditSecondTypeAll;
import com.gc.ViewModel.credit.CreditStatusAll;
import com.gc.ViewModel.credit.CreditThirdTypeAll;
import com.gc.model.Credit;
import com.gc.model.CreditFirstType;
import com.gc.model.CreditStatus;
import com.gc.repository.repository.CreditFirstTypeRepository;
import com.gc.repository.repository.CreditSecondTypeRepository;
import com.gc.repository.repository.CreditStatusRepository;
import com.gc.repository.repository.CreditThirdTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/credit")
@RestController
public class CreditController {
    @Autowired
    private CreditStatusRepository creditStatusRepository;
    @Autowired
    private CreditFirstTypeRepository creditFirstTypeRepository;


    @RequestMapping(value = "/getCreditStatuses")
    @ResponseBody
    public CreditStatusAll getCreditStatuses() {
        try {
            List<CreditStatus> creditStatuses = creditStatusRepository.findAll();
            return new CreditStatusAll(0, creditStatuses);
        } catch (Exception exception) {
            return new CreditStatusAll(2);
        }
    }

    @RequestMapping(value = "/getCreditFirstTypes")
    @ResponseBody
    public CreditFirstTypeAll getCreditFirstTypes() {
        try {
            List<CreditFirstType> firstTypes = creditFirstTypeRepository.findAll();
            return new CreditFirstTypeAll(0, firstTypes);
        } catch (Exception exception) {
            return new CreditFirstTypeAll(2);
        }
    }

    @RequestMapping(value = "/getCreditSecondTypes")
    @ResponseBody
    public CreditSecondTypeAll getCreditSecondTypes(@RequestParam(value = "credit_first_type_id")Long firstTypeId) {
        try {
            CreditFirstType firstType = creditFirstTypeRepository.findOne(firstTypeId);
            return new CreditSecondTypeAll(0, firstType.getCreditSecondTypeList());
        } catch (Exception exception) {
            return new CreditSecondTypeAll(2);
        }
    }


    @RequestMapping(value = "/getCreditThirdTypes")
    @ResponseBody
    public CreditThirdTypeAll getCreditThirdTypes(@RequestParam(value = "credit_first_type_id")Long firstTypeId) {
        try {
            CreditFirstType firstType = creditFirstTypeRepository.findOne(firstTypeId);
            return new CreditThirdTypeAll(0, firstType.getCreditThirdTypeList());
        } catch (Exception exception) {
            return new CreditThirdTypeAll(2);
        }
    }
}
