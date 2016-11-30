package com.gc.ViewModel.community;

import com.gc.model.Comment;
import com.gc.model.User;
import com.gc.model.Vote;
import com.gc.model.VoteItem;

import java.sql.Timestamp;
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

    private Long comment_id;
    private String content;
    private Long user_id;
    private String user_name;
    private Timestamp create_time;
    private ArrayList<CommunityRecomment> recomments;


    public CommunityComment(Comment comment) {
        this.comment_id = comment.getId();
        this.content = comment.getContent();
        User from_user = comment.getFromUser();
        this.user_id = from_user.getId();
        this.user_name = from_user.getUsername();
        this.create_time = comment.getCreate_time();
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public ArrayList<CommunityRecomment> getRecomments() {
        return recomments;
    }

    public void setRecomments(ArrayList<CommunityRecomment> recomments) {
        this.recomments = recomments;
    }
}

class CommunityRecomment {

    private Long comment_id;
    private String content;
    private Long user_id;
    private String user_name;
    private Timestamp create_time;
    private Long to_user_id;
    private String to_user_name;

    public CommunityRecomment(Comment comment) {
        this.comment_id = comment.getId();
        this.content = comment.getContent();
        User from_user = comment.getFromUser();
        this.user_id = from_user.getId();
        this.user_name = from_user.getUsername();
        this.create_time = comment.getCreate_time();
        User to_user = comment.getToUser();
        this.to_user_id = to_user.getId();
        this.to_user_name = to_user.getUsername();
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Long getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(Long to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getTo_user_name() {
        return to_user_name;
    }

    public void setTo_user_name(String to_user_name) {
        this.to_user_name = to_user_name;
    }
}
