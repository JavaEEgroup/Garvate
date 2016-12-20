package com.gc.controller;

import com.gc.ViewModel.user.AdminAllStudent;
import com.gc.model.User;
import com.gc.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

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
}
