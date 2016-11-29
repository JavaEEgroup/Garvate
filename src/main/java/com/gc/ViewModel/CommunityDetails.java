package com.gc.ViewModel;

import java.util.ArrayList;

/**
 * Created by jx-pc on 2016/11/29.
 */
public class CommunityDetails {

    private int state;
    private String title;
    private Integer id;
    private Integer user_id;
    private Integer view_count;
    private ArrayList<String> tag;
    private String create_time;
    private String update_time;
    private Integer vote_id;

    public CommunityDetails(int state) {
        this.state = state;
    }
}
