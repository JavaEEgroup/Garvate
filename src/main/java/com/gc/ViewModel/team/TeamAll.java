package com.gc.ViewModel.team;

import com.gc.model.Tag;
import com.gc.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamAll {
    private int status;
    private List<TeamRoughInf> results;
    private int page_sum;

    public TeamAll(){

    }

    public TeamAll(int state, List<Team> teams) {
        this(state);

        this.results = new ArrayList<>();
        for (Team team : teams) {
            results.add(new TeamRoughInf(team));
        }
    }

    public int getPage_sum() {
        return page_sum;
    }

    public void setPage_sum(int page_sum) {
        this.page_sum = page_sum;
    }

    public TeamAll(int state) {
        this.status = state;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TeamRoughInf> getResults() {
        return results;
    }

    public void setResults(List<TeamRoughInf> results) {
        this.results = results;
    }
}

class TeamRoughInf{
    private String title;
    private Long id;
    private String description;
    private Long user_id;
    private String user_name;
    private String create_time;
    private String update_time;
    private List<String> tag;

    public TeamRoughInf() {

    }

    public TeamRoughInf(Team team) {
        this.title = team.getTitle();
        this.id = team.getId();
        this.description = team.getDescription();
        this.user_id = team.getCaptain().getId();
        this.user_name = team.getCaptain().getUsername();
        this.create_time = team.getCreateTime().toString();
        this.update_time = team.getUpdateTime().toString();

        this.tag = new ArrayList<>();
        this.tag.addAll(team.getTagList().stream().map(Tag::getDescription).collect(Collectors.toList()));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
}
