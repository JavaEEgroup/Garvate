package com.gc.Utils;

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
}
