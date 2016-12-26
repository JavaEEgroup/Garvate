package com.gc.ViewModel.news;


public class News {

    private String create_time;
    private String title;
    private String content;
    private String url;
    private boolean visible;

    public News() {
    }

    public News(com.gc.model.News news) {
        this.create_time = news.getCreate_time().toString();
        this.title = news.getTitle();
        this.content = news.getContent();
        this.url = news.getUrl();
        this.visible = news.isVisible();
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
