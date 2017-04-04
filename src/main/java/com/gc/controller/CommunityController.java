package com.gc.controller;

import com.gc.Utils.Utils;
import com.gc.ViewModel.Entry;
import com.gc.ViewModel.community.CommunityAdd;
import com.gc.ViewModel.community.CommunityAll;
import com.gc.ViewModel.community.CommunityDetails;
import com.gc.model.*;
import com.gc.repository.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/community")
public class CommunityController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteItemRepository voteItemRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    private CommunityAll all(
            HttpServletRequest request,
            @RequestParam(value = "numResults",defaultValue = "20")int numResults,
            @RequestParam(value = "resultOffset", defaultValue = "0")int resultOffset) {

        try{
            Page<Article> articles = articleRepository.findAll(new PageRequest(resultOffset,numResults));

            CommunityAll communityAll = new CommunityAll(0);
            communityAll.add2Results(articles.getContent());
            communityAll.setPage_sum(articles.getTotalPages());

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);
            communityAll.setUser_id(user.getId());
            communityAll.setUsername(user.getUsername());

            return communityAll;
        }
        catch (Exception exception){
            return new CommunityAll(1);
        }
    }

    @RequestMapping(value = "/details")
    private CommunityDetails details(
            HttpServletRequest request,
            @RequestParam(value = "id")Long id) {


        try {
            CommunityDetails communityDetails = new CommunityDetails(0);

            Article article = articleRepository.getOne(id);
            article.addView_Count();
            articleRepository.save(article);

            User user = article.getUser();
            communityDetails.setUser_id(user.getId());
            communityDetails.setUsername(user.getUsername());

            ArrayList<Integer> userVotes = new ArrayList<>();
            if (article.getVote() != null) {
                for(VoteItem voteItem : user.getVoteItemList()) {
                    for(VoteItem voteItem1 : article.getVote().getVoteItemList())
                        if(Objects.equals(voteItem.getId(), voteItem1.getId())) {
                            userVotes.add(voteItem.getRank());
                        }
                }
            }

            communityDetails.setUser_votes(userVotes);

            communityDetails.add2CommunityDetails(article);

            article.setView_count(article.getView_count());

            return communityDetails;
        }
        catch (Exception exception) {
            return new CommunityDetails(1);
        }

    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private CommunityAdd add(HttpServletRequest request,
                      @RequestParam(value = "content")String content,
                      @RequestParam(value = "title")String title,

                      @RequestParam(value = "vote_desc",defaultValue = "")String vote_desc,
                      @RequestParam(value = "vote_item",defaultValue = "")List<String> vote_item,
                      @RequestParam(value = "vote_max",defaultValue = "0")int vote_max,
                      @RequestParam(value = "vote_min",defaultValue = "0")int vote_min,
                      @RequestParam(value = "vote_title",defaultValue = "")String vote_title,

                      @RequestParam(value = "tag_id",defaultValue = "")List<Long> tagIDs) {

        try {

            Timestamp nowTimestamp = Utils.getCurrentTime();

            List<Tag> tags = tagRepository.getTagsByTagIDs(tagIDs);
            for(Tag tag : tags) System.out.println("TAG--->" + tag.getDescription());
            Article article = new Article(title, content, nowTimestamp, tags);

            if(vote_title != null && !Objects.equals(vote_title, "")) {

                Vote vote = new Vote(vote_title, vote_desc, vote_max, vote_min);

                ArrayList<VoteItem> items = new ArrayList<>();

                int count = 0;

                for(String vote_name : vote_item) {

                    items.add(new VoteItem(vote_name, count));
                    count++;
                }

                vote.setVoteItemList(items);

                article.setVote(vote);

                voteRepository.save(vote);
            }

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);
            article.setUser(user);

            articleRepository.save(article);

            return new CommunityAdd(0, article.getId());
        } catch (Exception exception) {
            return new CommunityAdd(2, 0L);
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private Entry delete(HttpServletRequest request,
                             @RequestParam(value = "article_id")Long article_id) {

        articleRepository.delete(article_id);

        return new Entry(0);
    }

    @RequestMapping(value = "/vote/delete", method = RequestMethod.POST)
    private Entry deleteVote(HttpServletRequest request,
                                   @RequestParam(value = "vote_id")Long vote_id) {

        voteRepository.delete(vote_id);

        return new Entry(0);
    }

    @RequestMapping(value = "/vote/item/delete", method = RequestMethod.POST)
    private Entry deleteVoteItem(HttpServletRequest request,
                         @RequestParam(value = "vote_item_id")Long vote_item_id) {

        voteItemRepository.delete(vote_item_id);

        return new Entry(0);
    }

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    private Entry addComment(HttpServletRequest request,
                              @RequestParam(value = "content")String content,
                              @RequestParam(value = "id")Long id) {

        Article article = articleRepository.findOne(id);
        String user_account = request.getRemoteUser();
        User user = userRepository.findByAccount(user_account);

        Comment comment = new Comment(content, user, article);

        commentRepository.save(comment);

        String title = "评论提醒";
        String newsContent = "用户" + user.getUsername() + "评论了您的帖子。";
        String url = "/communities/" + id;
        News news = new News(title, newsContent, url, article.getUser());
        newsRepository.save(news);

        return new Entry(0);
    }

    @RequestMapping(value = "/vote/add", method = RequestMethod.POST)
    private Entry addVote(HttpServletRequest request,
                          @RequestParam(value = "vote_id")Long id) {

        try {
            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);
            System.out.println("---------->" + id);
            VoteItem voteItem = voteItemRepository.findOne(id);
            List<User> userList = voteItem.getUserList();
            userList.add(user);
            voteItem.setUserList(userList);
            voteItemRepository.save(voteItem);
            return new Entry(0);
        } catch (Exception e) {
            return new Entry(1);
        }
    }


    @RequestMapping(value = "/recomment/add", method = RequestMethod.POST)
    private Entry addRecomment(HttpServletRequest request,
                              @RequestParam(value = "content")String content,
                              @RequestParam(value = "article_id")Long article_id,
                              @RequestParam(value = "parent_comment_id")Long parent_comment_id,
                              @RequestParam(value = "to_user_id")Long to_user_id) {

        Article article = articleRepository.findOne(article_id);
        Comment parent_comment = commentRepository.findOne(parent_comment_id);
        String user_account = request.getRemoteUser();
        User user = userRepository.findByAccount(user_account);
        User toUser = userRepository.findOne(to_user_id);

        Comment comment = new Comment(content, user, toUser, parent_comment, article);

        commentRepository.save(comment);

        String title = "评论回复提醒";
        String newsContent = "用户" + user.getUsername() + "回复了您的评论。";
        String url = "/communities/" + article_id;
        News news = new News(title, newsContent, url, toUser);
        newsRepository.save(news);

        return new Entry(0);
    }

    @RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
    private Entry deleteComment(HttpServletRequest request,
                                  @RequestParam(value = "id")Long id) {

        for(Comment comment : commentRepository.findAll()) {
            Comment parentComment = comment.getParent_comment();
            if(parentComment != null && parentComment.getId() == id) {
                commentRepository.delete(comment);
            }
        }

        commentRepository.delete(id);

        return new Entry(0);
    }

    @RequestMapping(value = "/recomment/delete", method = RequestMethod.POST)
    private Entry deleteRecomment(HttpServletRequest request,
                                @RequestParam(value = "id")Long id) {

        commentRepository.delete(id);

        return new Entry(0);
    }

    @RequestMapping(value = "/search")
    public CommunityAll search(@RequestParam(value = "tag_desc",defaultValue = "") String tagDesc,
                          @RequestParam(value = "username",defaultValue = "") String username,
                          @RequestParam(value = "key",defaultValue = "!@#")String key,
                          @RequestParam(value = "numResults",defaultValue = "20")int numResults,
                          @RequestParam(value = "resultOffset", defaultValue = "0")int resultOffset) {
        try {
            List<Article> articles = articleRepository.findArticleByTagDescription(new PageRequest(resultOffset,numResults), tagDesc,username,key);
            CommunityAll communityAll = new CommunityAll(0);
            communityAll.add2Results(articles);
            communityAll.setPage_sum(1);
            return communityAll;
        }
        catch (Exception e) {
            return new CommunityAll(1);
        }
    }
}
