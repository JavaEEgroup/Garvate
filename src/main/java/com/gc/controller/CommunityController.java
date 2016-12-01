package com.gc.controller;

import com.gc.ViewModel.Entry;
import com.gc.ViewModel.community.CommunityAdd;
import com.gc.ViewModel.community.CommunityAll;
import com.gc.ViewModel.community.CommunityDetails;
import com.gc.model.*;
import com.gc.repository.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    @RequestMapping(value = "/all")
    private CommunityAll all() {

        CommunityAll communityAll = new CommunityAll(0);
        communityAll.add2Results(articleRepository.findAll());

        return communityAll;
    }

    @RequestMapping(value = "/details")
    private CommunityDetails details(HttpServletRequest request,
            @RequestParam(value = "id")Long id) {

        CommunityDetails communityDetails = new CommunityDetails(0);

        Article article = articleRepository.getOne(id);

        String user_account = request.getRemoteUser();
        User user = userRepository.findByAccount(user_account);

        ArrayList<Integer> userVotes = new ArrayList<>();
        for(VoteItem voteItem : user.getVoteItemList()) {
            for(VoteItem voteItem1 : article.getVote().getVoteItemList())
            if(Objects.equals(voteItem.getId(), voteItem1.getId())) {
                userVotes.add(voteItem.getRank());
            }
        }

        communityDetails.setUser_votes(userVotes);

        communityDetails.add2CommunityDetails(article);

        return communityDetails;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private CommunityAdd add(HttpServletRequest request,
                      @RequestParam(value = "content")String content,
                      @RequestParam(value = "title")String title,
                      @RequestParam(value = "vote_desc")String vote_desc,
                      @RequestParam(value = "vote_item")List<String> vote_item,
                      @RequestParam(value = "vote_max")int vote_max,
                      @RequestParam(value = "vote_min")int vote_min,
                      @RequestParam(value = "vote_title")String vote_title,
                      @RequestParam(value = "tag_id")List<Long> tagIDs) {

        try {
            Date date = new Date();

            Timestamp nowTimestamp = new Timestamp(date.getTime());

            List<Tag> tags = tagRepository.getTagsByTagIDs(tagIDs);
            for(Tag tag : tags) System.out.println("TAG--->" + tag.getDescription());
            Article article = new Article(title, content, nowTimestamp, tags);

            if(vote_title != null) {

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

        }

        return new CommunityAdd(0, 0L);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private Entry delete(HttpServletRequest request,
                             @RequestParam(value = "article_id")Long article_id) {

        articleRepository.delete(article_id);

        return new Entry(1);
    }

    @RequestMapping(value = "/delete/vote", method = RequestMethod.POST)
    private Entry delete_vote(HttpServletRequest request,
                                   @RequestParam(value = "vote_id")Long vote_id) {

        voteRepository.delete(vote_id);

        return new Entry(1);
    }

    @RequestMapping(value = "/delete/vote/item", method = RequestMethod.POST)
    private Entry delete_vote_item(HttpServletRequest request,
                         @RequestParam(value = "vote_item_id")Long vote_item_id) {

        voteItemRepository.delete(vote_item_id);

        return new Entry(1);
    }

    @RequestMapping(value = "/show", method = RequestMethod.POST)
    private Entry show(HttpServletRequest request,
                                   @RequestParam(value = "id")Long id) {

        Article article = articleRepository.findOne(id);
        Vote vote = article.getVote();
        Article a = vote.getArticle();
        System.out.println(a.getContent());

        return new Entry(1);
    }
}
