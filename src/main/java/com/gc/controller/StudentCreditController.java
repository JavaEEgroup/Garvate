package com.gc.controller;

import com.gc.Utils.Config;
import com.gc.ViewModel.Entry;
import com.gc.ViewModel.credit.CreditBriefInf;
import com.gc.model.*;
import com.gc.repository.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@RequestMapping(value = "/student/credit")
@RestController
public class StudentCreditController {

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


    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Entry add(HttpServletRequest request,
                     @RequestParam(value = "name")String name,
                     @RequestParam(value = "get_prize_time")Timestamp getPrizeTime,
                     @RequestParam(value = "credit_first_type_id")Long creditFirstTypeId,
                     @RequestParam(value = "credit_second_type_id")Long creditSecondTypeId,
                     @RequestParam(value = "credit_third_type_id")Long creditThirdTypeId){

        try {
            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            CreditFirstType firstType = creditFirstTypeRepository.findOne(creditFirstTypeId);
            CreditSecondType secondType = creditSecondTypeRepository.findOne(creditSecondTypeId);
            CreditThirdType thirdType = creditThirdTypeRepository.findOne(creditThirdTypeId);
            CreditStatus creditStatus = creditStatusRepository.findByDescription(Config.CREDIT_STATUS_APPLY);

            Credit credit = new Credit(user, name, getPrizeTime, firstType, secondType, thirdType);
            credit.setCreditStatus(creditStatus);
            creditRepository.save(credit);
            return new CreditBriefInf(0, credit.getId());
        } catch (Exception exception) {
            return new Entry(2);
        }
    }

}
