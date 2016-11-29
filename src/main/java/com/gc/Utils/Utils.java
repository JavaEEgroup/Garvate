package com.gc.Utils;

import java.util.HashMap;

public class Utils {
    public static HashMap<String,String> getStateMessage(String state){
        HashMap<String, String> results = new HashMap<>();
        results.put("state",state);
        return results;
    }
}
