package com.gc.ViewModel.news;


import java.util.ArrayList;
import java.util.List;

public class NewsList {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    private int status;

    private List<News> results;

    private int page_sum;

    public NewsList() {
    }

    public NewsList(int status) {
        this.status = status;
    }

    public NewsList(int status, List<com.gc.model.News> results, int page_sum) {
        this.status = status;
        this.results =  new ArrayList<>();
        for(com.gc.model.News news : results) {
            this.results.add(new News(news));
        }
        this.page_sum = page_sum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<News> getResults() {
        return results;
    }

    public void setResults(List<News> results) {
        this.results = results;
    }

    public int getPage_sum() {
        return page_sum;
    }

    public void setPage_sum(int page_sum) {
        this.page_sum = page_sum;
    }
}
