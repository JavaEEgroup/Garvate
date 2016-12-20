package com.gc.controller;

import com.gc.ViewModel.user.AdminAllStudent;
import com.gc.ViewModel.user.InfCredit;
import com.gc.model.Credit;
import com.gc.model.User;
import com.gc.repository.repository.CreditRepository;
import com.gc.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CreditRepository creditRepository;

    private User checkIfAdmin(HttpServletRequest request) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            if(user.getRoleList().get(0).getDesc().equals("admin"))
                return user;

            return null;
        }
        catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/allStudent")
    @ResponseBody
    public AdminAllStudent getAllStudent(
            HttpServletRequest request) {
        try {

            User admin = checkIfAdmin(request);
            if(admin == null) return new AdminAllStudent(1);

            return new AdminAllStudent(0, userRepository.findAll());
        } catch (Exception exception) {
            return new AdminAllStudent(1);
        }
    }

    @RequestMapping(value = "/infCredit", method = RequestMethod.POST)
    @ResponseBody
    public InfCredit getInfCredit(
            HttpServletRequest request,
            @RequestParam(value = "id")Long id) {
        try {

            User admin = checkIfAdmin(request);
            if(admin == null) return new InfCredit(1);

            User user = userRepository.findOne(id);
            List<Credit> credits = creditRepository.findByUser(user);

            return new InfCredit(0, user, credits);
        } catch (Exception exception) {
            return new InfCredit(1);
        }
    }
}
