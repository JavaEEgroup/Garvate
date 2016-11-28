package com.gc;

import java.util.HashMap;

/**
 * Created by zihe on 2016/11/28.
 */
public class Utils {
    public static HashMap<String,String> getStateMessage(String state){
        HashMap<String, String> results = new HashMap<>();
        results.put("state",state);
        return results;
    }
}
