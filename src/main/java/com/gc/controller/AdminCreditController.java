package com.gc.controller;

import com.gc.Utils.Config;
import com.gc.ViewModel.Entry;
import com.gc.ViewModel.credit.CreditAllWithUser;
import com.gc.model.Credit;
import com.gc.model.CreditStatus;
import com.gc.model.User;
import com.gc.repository.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/admin/credit")
@RestController
public class AdminCreditController {
    @Autowired
    private CreditRepository creditRepository;
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

    @RequestMapping(value = "/update")
    @ResponseBody
    public Entry changeCreditStatus(@RequestParam(value = "credit_id") Long creditId,
                                    @RequestParam(value = "credit_status_id") Long creditStatusId,
                                    @RequestParam(value = "grade",defaultValue = "0")int grade,
                                    @RequestParam(value = "value",defaultValue = "0")int value) {
        try {
            CreditStatus creditStatus = creditStatusRepository.findOne(creditStatusId);
            Credit credit = creditRepository.findOne(creditId);
            if (Objects.equals(creditStatus.getDescription(), Config.CREDIT_STATUS_PASS)) {
                credit.setGrade(grade);
                credit.setValue(value);
            }
            credit.setCreditStatus(creditStatus);
            return new Entry(0);
        } catch (Exception exception) {
            return new Entry(2);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Entry delete(@RequestParam(value = "credit_id") Long creditId) {
        try{

            Credit credit = creditRepository.findOne(creditId);
            creditRepository.delete(credit);
            return new Entry(0);
        }
        catch (Exception exception){
            return new Entry(2);
        }
    }
}