package com.gc.controller;

import com.gc.ViewModel.credit.CreditAllWithUser;
import com.gc.model.Credit;
import com.gc.repository.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/admin/credit")
@RestController
public class AdminCreditController {
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private CreditFirstTypeRepository creditFirstTypeRepository;
    @Autowired
    private CreditSecondTypeRepository creditSecondTypeRepository;
    @Autowired
    private CreditThirdTypeRepository creditThirdTypeRepository;
    @Autowired
    private CreditStatusRepository creditStatusRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/all")
    @ResponseBody
    public CreditAllWithUser getAll(@RequestParam(value = "numResults",defaultValue = "20")int numResults,
                                    @RequestParam(value = "resultOffset", defaultValue = "0")int resultOffset){
        try {
            List<Credit> credits = creditRepository.findAll(new PageRequest(resultOffset,numResults)).getContent();
            return new CreditAllWithUser(0, credits);
        } catch (Exception exception) {
            return new CreditAllWithUser(2);
        }
    }



}