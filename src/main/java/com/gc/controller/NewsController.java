package com.gc.controller;

import com.gc.ViewModel.news.NewsDetail;
import com.gc.ViewModel.news.NewsList;
import com.gc.model.News;
import com.gc.model.User;
import com.gc.repository.repository.NewsRepository;
import com.gc.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/news")
public class NewsController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    NewsRepository newsRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public NewsList getUserNews(
            HttpServletRequest request,
            @RequestParam(value = "numResults",defaultValue = "20")int numResults,
            @RequestParam(value = "resultOffset", defaultValue = "0")int resultOffset) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            Page<News> newses = newsRepository.findByUser(user, new PageRequest(resultOffset,numResults));

            return new NewsList(NewsList.SUCCESS, newses.getContent(), newses.getTotalPages());
//            return new NewsList(NewsList.ERROR);
        }
        catch (Exception e) {
            return new NewsList(NewsList.ERROR);
        }
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public NewsDetail getNewsDetails(
            HttpServletRequest request,
            @RequestParam(value = "id")Long id) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            News news = newsRepository.findOne(id);

            if(news == null) return new NewsDetail(NewsDetail.NEWS_NOT_FOUND);

            if(!news.getUser().getId().equals(user.getId())) return new NewsDetail(NewsDetail.NO_AUTHORITY);

            news.setVisible(false);
            newsRepository.save(news);

            return new NewsDetail(NewsDetail.SUCCESS, news);
        }
        catch (Exception e) {
            return new NewsDetail(NewsDetail.ERROR);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public NewsDetail addNews(
            HttpServletRequest request,
            @RequestParam(value = "title")String title,
            @RequestParam(value = "content")String content,
            @RequestParam(value = "url")String url) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            News news = new News(title, content, true, url, user);

            newsRepository.save(news);

            return new NewsDetail(NewsDetail.SUCCESS);
        }catch (Exception e) {
            return new NewsDetail(NewsDetail.ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public NewsDetail deleteNews(
            HttpServletRequest request,
            @RequestParam(value = "id")Long id) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            News news = newsRepository.findOne(id);

            if(news == null) return new NewsDetail(NewsDetail.NEWS_NOT_FOUND);

            if(!news.getUser().getId().equals(user.getId())) return new NewsDetail(NewsDetail.NO_AUTHORITY);

            newsRepository.delete(id);

            return new NewsDetail(NewsDetail.SUCCESS);

        }catch (Exception e) {
            return new NewsDetail(NewsDetail.ERROR);
        }
    }
}
