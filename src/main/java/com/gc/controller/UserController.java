package com.gc.controller;

import com.gc.Utils;
import com.gc.model.User;
import com.gc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login_success")
    @ResponseBody
    public HashMap<String,String> login_success(HttpServletRequest request) {
//        String user_account =  request.getRemoteUser();
        return Utils.getStateMessage("0");
    }

    @RequestMapping(value = "/login_fail")
    @ResponseBody
    public  HashMap<String, String> login_fail(HttpServletRequest request) {
        return Utils.getStateMessage("2");
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public HashMap<String, String> login() {
        return Utils.getStateMessage("1");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> register(@RequestParam(value = "account")String account,
                            @RequestParam(value = "password")String password,
                            @RequestParam(value="username")String username) {
        try {
            String encodePassword = new BCryptPasswordEncoder().encode(password);
            User user = new User();
            user.setAccount(account);
            user.setPassword(encodePassword);
            user.setEnabled(true);
            user.setUsername(username);
            userRepository.save(user);

            HashMap<String,String> results =  Utils.getStateMessage("0");
            results.put("user_id", ""+user.getId());
            return results;
        } catch (Exception exception) {
            return Utils.getStateMessage("2");
        }
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() {
        return "test";
    }
}
