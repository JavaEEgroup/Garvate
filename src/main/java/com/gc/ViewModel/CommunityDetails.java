package com.gc.ViewModel;

import com.gc.model.Vote;
import com.gc.model.VoteItem;

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
    private ArrayList<CommunityVote> vote;
    private ArrayList<CommunityVotes> votes;
    private ArrayList<Integer> user_votes;

    public CommunityDetails(int state) {
        this.state = state;
    }


}

class CommunityVote {

    private String title;
    private String desc;
    private Integer max;
    private Integer min;

    public CommunityVote(Vote vote) {
        this.title = vote.getTitle();
        this.desc = vote.getDescription();
        this.max = vote.getMax();
        this.min = vote.getMin();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }
}

class CommunityVotes {

    private String title;
    private Integer rank;
    private int vote_count;

    public CommunityVotes(VoteItem voteItem) {
        this.title = voteItem.getTitle();
        this.rank = voteItem.getRank();
        this.vote_count = voteItem.getUserList().size();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}

class CommunityComment {

    
}
