package com.gc.ViewModel.team;

import com.gc.model.ProjectType;
import com.gc.model.Tag;
import com.gc.model.Team;
import com.gc.model.TeamUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamDetails {
    private int status;

    private TeamDetailInf results;

    private Boolean create;

    private List<com.gc.ViewModel.project.ProjectType> types;

    public TeamDetails() {

    }

    public TeamDetails(int state) {
        this.status = state;
    }

    public TeamDetails(int state, Team team) {
        this(state);

        results = new TeamDetailInf(team);
    }

    public TeamDetails(int state, Team team, Boolean create, List<ProjectType> types) {
        this(state);
        this.create = (create != null) && create;
        this.types = new ArrayList<>();
        for(ProjectType type : types) {
            this.types.add(new com.gc.ViewModel.project.ProjectType(type));
        }
        results = new TeamDetailInf(team);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TeamDetailInf getResults() {
        return results;
    }

    public void setResults(TeamDetailInf results) {
        this.results = results;
    }

    public Boolean getCreate() {
        return create;
    }

    public void setCreate(Boolean create) {
        this.create = create;
    }

    public List<com.gc.ViewModel.project.ProjectType> getTypes() {
        return types;
    }

    public void setTypes(List<com.gc.ViewModel.project.ProjectType> types) {
        this.types = types;
    }
}

class TeamDetailInf{
    private String captain_name;
    private Long captain_id;

    private int max_count;
    private int current_count;

    private String team_name;
    private String team_description;

    private String create_time;
    private String update_time;

    private boolean team_state;

    private List<String> tags;

    private List<TeamMember> team_members;

    TeamDetailInf(Team team) {
        this.captain_name = team.getCaptain().getUsername();
        this.captain_id = team.getCaptain().getId();

        this.max_count = team.getMaxCount();
        this.current_count = team.getCurrentCount();

        this.create_time = team.getCreateTime().toString();
        this.update_time = team.getUpdateTime().toString();

        this.team_state = team.isState();

        this.team_name = team.getTitle();
        this.team_description = team.getDescription();

        this.tags = new ArrayList<>();
        this.tags.addAll(team.getTagList().stream().map(Tag::getDescription).collect(Collectors.toList()));

        this.team_members =  new ArrayList<>();
        List<TeamUser> users = team.getTeamUserList();
        this.team_members.addAll(users.stream().map(TeamMember::new).collect(Collectors.toList()));
    }

    public String getCaptain_name() {
        return captain_name;
    }

    public void setCaptain_name(String captain_name) {
        this.captain_name = captain_name;
    }

    public Long getCaptain_id() {
        return captain_id;
    }

    public void setCaptain_id(Long captain_id) {
        this.captain_id = captain_id;
    }

    public int getMax_count() {
        return max_count;
    }

    public void setMax_count(int max_count) {
        this.max_count = max_count;
    }

    public int getCurrent_count() {
        return current_count;
    }

    public void setCurrent_count(int current_count) {
        this.current_count = current_count;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_description() {
        return team_description;
    }

    public void setTeam_description(String team_description) {
        this.team_description = team_description;
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

    public boolean isTeam_state() {
        return team_state;
    }

    public void setTeam_state(boolean team_state) {
        this.team_state = team_state;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<TeamMember> getTeam_members() {
        return team_members;
    }

    public void setTeam_members(List<TeamMember> team_members) {
        this.team_members = team_members;
    }
}

class TeamMember{
    private String user_name;
    private Long user_id;
    private String state;

    TeamMember(TeamUser teamUser) {
        this.user_name = teamUser.getMember().getUsername();
        this.user_id = teamUser.getMember().getId();
        this.state = teamUser.getState().getDescription();
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
