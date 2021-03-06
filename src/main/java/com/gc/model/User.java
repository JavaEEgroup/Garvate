package com.gc.model;

import com.gc.Utils.Config;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;


    @Column(name = "account", nullable = false)
    private String account;


    @Column(name = "username", nullable = false, unique = true)
    private String username;


    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "phone")
    private String phone;


    @Column(name = "email")
    private String email;


    @Column(name = "type")
    private Integer type;

    @Column(name = "enabled")
    private Boolean enabled;


    @ManyToOne
    private Major major;


    @ManyToMany
    @JoinTable(
            name="user_tag",
            inverseJoinColumns = @JoinColumn(name = "tag_id"),
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Tag> tagList;

    @JoinColumn(name = "captain_id")
    @OneToMany
    private List<Team> captainTeamList;

    @JoinColumn(name = "member_user_id")
    @OneToMany
    private List<TeamUser> teamUserList;

    @JoinColumn(name = "user_id")
    @OneToMany
    private List<Article> articleList;

    @JoinColumn(name = "user_id")
    @OneToMany
    private List<News> newsList;

    @JoinColumn(name = "to_user_id")
    @OneToMany
    private List<Comment> toCommentList;

    @JoinColumn(name = "from_user_id")
    @OneToMany
    private List<Comment> fromCommentList;

    @ManyToMany(mappedBy = "userList")
    private List<VoteItem> voteItemList;

    @ManyToMany
    @JoinTable(
            name="user_role",
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Role> roleList;

    public List<Credit> getCreditList() {
        return creditList;
    }

    public void setCreditList(List<Credit> creditList) {
        this.creditList = creditList;
    }

    @JoinColumn(name = "user_id")
    @OneToMany
    private List<Credit> creditList;

    public List<Project> getJoinProject() {
        ArrayList<Project> projects = new ArrayList<>();
        for(TeamUser teamUser : teamUserList) {
            if (teamUser.getState().getDescription().equals(Config.TEAM_STATE_INTEAM)) {
                Project userProject = teamUser.getTeam().getProject();
                if(userProject != null){
                    projects.add(userProject);
                }
            }
        }
        return projects;
    }

    public List<Project> getCaptainProject() {
        ArrayList<Project> projects = new ArrayList<>();
        for(Team team : captainTeamList) {
            Project userProject = team.getProject();
            if(userProject != null) {
                projects.add(userProject);
            }
        }
        return projects;
    }

    public List<Project> getAllProject() {
        ArrayList<Project> projects = new ArrayList<>();
        projects.addAll(getCaptainProject());
        projects.addAll(getJoinProject());
        return projects;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Team> getCaptainTeamList() {
        return captainTeamList;
    }

    public void setCaptainTeamList(List<Team> captainTeamList) {
        this.captainTeamList = captainTeamList;
    }

    public List<TeamUser> getTeamUserList() {
        return teamUserList;
    }

    public void setTeamUserList(List<TeamUser> teamUserList) {
        this.teamUserList = teamUserList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<Comment> getToCommentList() {
        return toCommentList;
    }

    public void setToCommentList(List<Comment> toCommentList) {
        this.toCommentList = toCommentList;
    }

    public List<Comment> getFromCommentList() {
        return fromCommentList;
    }

    public void setFromCommentList(List<Comment> fromCommentList) {
        this.fromCommentList = fromCommentList;
    }

    public List<VoteItem> getVoteItemList() {
        return voteItemList;
    }

    public void setVoteItemList(List<VoteItem> voteItemList) {
        this.voteItemList = voteItemList;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
