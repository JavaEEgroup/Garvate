package com.gc.ViewModel.news;


import java.util.ArrayList;
import java.util.List;

public class NewsList {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    private int status;

    private List<News> results;

    public NewsList() {
    }

    public NewsList(int status) {
        this.status = status;
    }

    public NewsList(int status, List<com.gc.model.News> results) {
        this.status = status;
        this.results =  new ArrayList<>();
        for(com.gc.model.News news : results) {
            this.results.add(new News(news));
        }
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
}
