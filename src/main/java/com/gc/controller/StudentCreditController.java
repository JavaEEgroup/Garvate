package com.gc.controller;

import com.gc.Utils.Config;
import com.gc.ViewModel.Entry;
import com.gc.ViewModel.credit.CreditAll;
import com.gc.ViewModel.credit.CreditBriefInf;
import com.gc.model.*;
import com.gc.repository.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

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


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public CreditBriefInf add(HttpServletRequest request,
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
            return new CreditBriefInf(2);
        }
    }

    @RequestMapping(value = "/myCredits", method = RequestMethod.GET)
    @ResponseBody
    public CreditAll myCredits(HttpServletRequest request,
                               @RequestParam(value = "numResults",defaultValue = "20")int numResults,
                               @RequestParam(value = "resultOffset", defaultValue = "0")int resultOffset){
        try {
            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            List<Credit> credits = creditRepository.findByUser(new PageRequest(resultOffset, numResults), user);
            return new CreditAll(0, credits);
        } catch (Exception exception) {
            return new CreditAll(2);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Entry delete(HttpServletRequest request,
                        @RequestParam(value = "credit_id") Long creditId) {
        try{
            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            Credit credit = creditRepository.findOne(creditId);
            if (!Objects.equals(credit.getUser().getId(), user.getId())) {
                return new Entry(2);
            }
            creditRepository.delete(credit);
            return new Entry(0);
        }
        catch (Exception exception){
            return new Entry(2);
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public CreditBriefInf update(HttpServletRequest request,
                                 @RequestParam(value = "credit_id")Long creditId,
                                 @RequestParam(value = "name")String name,
                                 @RequestParam(value = "get_prize_time")Timestamp getPrizeTime,
                                 @RequestParam(value = "credit_first_type_id")Long creditFirstTypeId,
                                 @RequestParam(value = "credit_second_type_id")Long creditSecondTypeId,
                                 @RequestParam(value = "credit_third_type_id")Long creditThirdTypeId){

        try {
            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);
            Credit credit = creditRepository.findOne(creditId);
            if (!Objects.equals(credit.getUser().getId(), user.getId())) {
                return new CreditBriefInf(2);
            }

            CreditFirstType firstType = creditFirstTypeRepository.findOne(creditFirstTypeId);
            CreditSecondType secondType = creditSecondTypeRepository.findOne(creditSecondTypeId);
            CreditThirdType thirdType = creditThirdTypeRepository.findOne(creditThirdTypeId);
            CreditStatus creditStatus = creditStatusRepository.findByDescription(Config.CREDIT_STATUS_APPLY);

            credit.setName(name);
            credit.setCreditStatus(creditStatus);
            credit.setCreditFirstType(firstType);
            credit.setCreditSecondType(secondType);
            credit.setCreditThirdType(thirdType);
            credit.setGetPrizeTime(getPrizeTime);

            creditRepository.save(credit);
            return new CreditBriefInf(0, credit.getId());
        } catch (Exception exception) {
            return new CreditBriefInf(2);
        }
    }

}
