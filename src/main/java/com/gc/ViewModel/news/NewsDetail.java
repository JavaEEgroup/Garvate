package com.gc.ViewModel.news;


public class NewsDetail {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int NO_AUTHORITY = 2;
    public static final int NEWS_NOT_FOUND = 3;

    private int status;
    private News news;

    public NewsDetail() {

    }

    public NewsDetail(int status) {
        this.status = status;
    }

    public NewsDetail(int status, com.gc.model.News news) {
        this.status = status;
        this.news = new News(news);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
