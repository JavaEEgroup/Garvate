package com.gc.ViewModel.community;

import com.gc.model.*;

import java.util.ArrayList;
import java.util.List;


public class CommunityDetails {

    private int state;
    private String title;
    private Long id;
    private Integer user_id;
    private Integer view_count;
    private ArrayList<String> tag;
    private String create_time;
    private String update_time;
    private Long vote_id;
    private CommunityVote vote;
    private ArrayList<CommunityVotes> votes;
    private ArrayList<Integer> user_votes;
    private ArrayList<CommunityComment> comments;

    public CommunityDetails(int state) {
        this.state = state;
    }

    public void add2CommunityDetails(Article article) {
        this.title = article.getTitle();
        this.id = article.getId();
        this.view_count = article.getView_count();
        this.tag = new ArrayList<>();
        for(Tag tag :article.getTagList()) {
            this.tag.add(tag.getDescription());
        }
        this.create_time = article.getCreate_time().toString();
        this.update_time = article.getUpdate_time().toString();
        Vote article_vote = article.getVote();
        this.vote = new CommunityVote(article_vote);
        this.vote_id = article_vote.getId();
        this.votes = new ArrayList<>();
        for(VoteItem voteItem : article_vote.getVoteItemList()) {
            this.votes.add(new CommunityVotes(voteItem));
        }
        this.comments =  new ArrayList<>();
        List<Comment> article_comments = article.getCommentList();
        for(Comment comment : article_comments) {
            if(comment.getParent_comment() == null) {
                this.comments.add(new CommunityComment(comment, article_comments));
            }
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    public ArrayList<String> getTag() {
        return tag;
    }

    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public Long getVote_id() {
        return vote_id;
    }

    public void setVote_id(Long vote_id) {
        this.vote_id = vote_id;
    }

    public CommunityVote getVote() {
        return vote;
    }

    public void setVote(CommunityVote vote) {
        this.vote = vote;
    }

    public ArrayList<CommunityVotes> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<CommunityVotes> votes) {
        this.votes = votes;
    }

    public ArrayList<Integer> getUser_votes() {
        return user_votes;
    }

    public void setUser_votes(ArrayList<Integer> user_votes) {
        this.user_votes = user_votes;
    }

    public ArrayList<CommunityComment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommunityComment> comments) {
        this.comments = comments;
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

    public void add2CommunityVote(Vote vote) {
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
    private String create_time;
    private ArrayList<CommunityRecomment> recomments;


    public CommunityComment(Comment comment, List<Comment> comments) {
        this.comment_id = comment.getId();
        this.content = comment.getContent();
        User from_user = comment.getFromUser();
        this.user_id = from_user.getId();
        this.user_name = from_user.getUsername();
        this.create_time = comment.getCreate_time().toString();
        this.recomments = new ArrayList<>();
        for(Comment comment1 : comments) {
            Comment parent_comment = comment1.getParent_comment();
            if(parent_comment == null) continue;
            else if(parent_comment.getId() == this.comment_id) {
                this.recomments.add(new CommunityRecomment(comment1));
            }
        }
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
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
    private String create_time;
    private Long to_user_id;
    private String to_user_name;

    public CommunityRecomment(Comment comment) {
        this.comment_id = comment.getId();
        this.content = comment.getContent();
        User from_user = comment.getFromUser();
        this.user_id = from_user.getId();
        this.user_name = from_user.getUsername();
        this.create_time = comment.getCreate_time().toString();
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
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
