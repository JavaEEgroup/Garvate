package com.gc.Utils;

import com.gc.model.User;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class Utils {
    public static HashMap<String, Long> getStateMessage(Long status){
        HashMap<String, Long> results = new HashMap<>();
        results.put("status", status);
        return results;
    }

    public static Timestamp getCurrentTime() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public static boolean checkAdmin(User user) {

        if(user.getRoleList().get(0).getDesc().equals("admin")) {
            return true;
        }

        return false;
    }
}
