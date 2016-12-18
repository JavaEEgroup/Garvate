package com.gc.ViewModel.Tag;


import com.gc.model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TagsAll {
    private int status;
    private List<TagInfo> results;

    public TagsAll() {

    }

    public TagsAll(int status) {
        this();
        this.status = status;
    }

    public TagsAll(int status, List<Tag> tags) {
        this(status);
        results = new ArrayList<>();
        this.results.addAll(tags.stream().map(TagInfo::new).collect(Collectors.toList()));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TagInfo> getResults() {
        return results;
    }

    public void setResults(List<TagInfo> results) {
        this.results = results;
    }
}

class TagInfo{
    private Long id;
    private String description;

    TagInfo() {

    }

    TagInfo(Tag tag) {
        this.id = tag.getId();
        this.description = tag.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
