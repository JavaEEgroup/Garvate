package com.gc.controller;

import com.gc.Utils.Config;
import com.gc.Utils.Utils;
import com.gc.model.Role;
import com.gc.model.User;
import com.gc.repository.repository.RoleRepository;
import com.gc.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminUserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Long> createUser(@RequestParam(value = "account")String account,
                                         @RequestParam(value = "password")String password,
                                         @RequestParam(value = "role")String roleStr) {
        try {
            String encodePassword = new BCryptPasswordEncoder().encode(password);
            User user = new User();
            user.setAccount(account);
            user.setPassword(encodePassword);
            user.setEnabled(true);
            user.setUsername(account);
            Role role = roleRepository.getByDescription(roleStr);
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            user.setRoleList(new ArrayList<>(roles));
            userRepository.save(user);

            HashMap<String,Long> results =  Utils.getStateMessage(Config.STATE_SUCCESS);
            results.put("user_id", user.getId());
            return results;
        } catch (Exception exception) {
            return Utils.getStateMessage(Config.STATE_FAIL_OTHER);
        }
    }

    @RequestMapping(value = "/createUserMultiply", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Long> createUserMultiply(@RequestParam(value = "fromAccount")String fromAccount,
                                                   @RequestParam(value = "toAccount")String toAccount,
                                                   @RequestParam(value = "password")String password,
                                                   @RequestParam(value = "role")String roleStr) {
        try {
            Role role = roleRepository.getByDescription(roleStr);
            List<Role> roles = new ArrayList<>();
            roles.add(role);

            for(int fromAccountInt = Integer.parseInt(fromAccount);fromAccountInt<=Integer.parseInt(toAccount);fromAccountInt++) {
                String encodePassword = new BCryptPasswordEncoder().encode(password);
                User user = new User();
                user.setAccount(fromAccountInt+"");
                user.setPassword(encodePassword);
                user.setEnabled(true);
                user.setUsername(fromAccountInt+"");
                user.setRoleList(new ArrayList<>(roles));
                userRepository.save(user);
            }


            return Utils.getStateMessage(Config.STATE_SUCCESS);
        } catch (Exception exception) {
            return Utils.getStateMessage(Config.STATE_FAIL_OTHER);
        }
    }

}
