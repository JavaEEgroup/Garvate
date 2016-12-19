package com.gc.controller;

import com.gc.Utils.Config;
import com.gc.Utils.Utils;
import com.gc.ViewModel.user.UserBriefInfo;
import com.gc.ViewModel.user.UserIds;
import com.gc.model.Role;
import com.gc.model.User;
import com.gc.repository.repository.RoleRepository;
import com.gc.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/login_success")
    @ResponseBody
    public HashMap<String,Long> login_success(HttpServletRequest request) {
        return Utils.getStateMessage(Config.STATE_SUCCESS);

    }

    @RequestMapping(value = "/login_fail")
    @ResponseBody
    public  HashMap<String, Long> login_fail(HttpServletRequest request) {
        return Utils.getStateMessage(Config.STATE_FAIL_OTHER);
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public HashMap<String, Long> login() {
        return Utils.getStateMessage(Config.STATE_FAIL_LOGIN);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Long> register(@RequestParam(value = "account")String account,
                            @RequestParam(value = "password")String password,
                            @RequestParam(value="username")String username) {
        try {
            String encodePassword = new BCryptPasswordEncoder().encode(password);
            User user = new User();
            user.setAccount(account);
            user.setPassword(encodePassword);
            user.setEnabled(true);
            user.setUsername(username);
            Role role = roleRepository.getByDescription(Config.ROLE_STUDENT);
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            user.setRoleList(new ArrayList<Role>(roles));
            userRepository.save(user);

            HashMap<String,Long> results =  Utils.getStateMessage(Config.STATE_SUCCESS);
            results.put("user_id", user.getId());
            return results;
        } catch (Exception exception) {
            return Utils.getStateMessage(Config.STATE_FAIL_OTHER);
        }
    }

    @RequestMapping(value = "/checkLogin",method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String,Long>  checkLogin(){
        return Utils.getStateMessage(Config.STATE_SUCCESS);
    }

    @RequestMapping(value = "/getUserBriefInf", method = RequestMethod.GET)
    @ResponseBody
    public UserBriefInfo getUserBriefInf(HttpServletRequest request) {

        try {
            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);
            return new UserBriefInfo(0, user);
        } catch (Exception exception) {
            return new UserBriefInfo(2);
        }
    }

    @RequestMapping(value = "/getByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserIds getByUsername(@RequestParam(value = "username") String username) {
        try {
            List<User> users = userRepository.findByUsernameLike("%"+username+"%");
            return new UserIds(0, users);
        } catch (Exception exception) {
            return new UserIds(2);
        }
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/roleTest")
    @ResponseBody
    public String roleTest() {
        return "roleTest";
    }
}
