package com.gc.Utils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class Utils {
    public static HashMap<String,String> getStateMessage(String state){
        HashMap<String, String> results = new HashMap<>();
        results.put("state",state);
        return results;
    }

    public static Timestamp getCurrentTime() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}
