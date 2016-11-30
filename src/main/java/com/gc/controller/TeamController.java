package com.gc.controller;

import com.gc.Utils.Config;
import com.gc.Utils.Utils;
import com.gc.ViewModel.team.TeamAll;
import com.gc.ViewModel.team.TeamDetails;
import com.gc.model.*;
import com.gc.repository.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@RestController
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TeamStateRepository teamStateRepository;
    @Autowired
    private TeamUserRepository teamUserRepository;

    @RequestMapping(value = "/team/add", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> add(HttpServletRequest request,
                                      @RequestParam(value = "description")String desc,
                                      @RequestParam(value = "max_count")int maxCount,
                                      @RequestParam(value = "name")String teamName,
                                      @RequestParam(value = "tag_id_list[]")List<Long> tagIDs){
        try {
            Date date = new Date();
            Timestamp nowTimestamp = new Timestamp(date.getTime());
            Team team = new Team(desc, teamName, maxCount, nowTimestamp, nowTimestamp);

            String user_account = request.getRemoteUser();
            User captain = userRepository.findByAccount(user_account);
            team.setCaptain(captain);

            List<Tag> tags = tagRepository.getTagsByTagIDs(tagIDs);
            team.setTagList(tags);

            teamRepository.save(team);

            HashMap<String, String> results = Utils.getStateMessage("0");
            results.put("team_id", "" + team.getId());
            return results;
        } catch (Exception exception) {
            return Utils.getStateMessage("2");
        }
    }

    @RequestMapping(value = "/team/all", method = RequestMethod.GET)
    public TeamAll getAll(@RequestParam(value = "numResults",defaultValue = "20")int numResults,
                          @RequestParam(value = "resultOffset", defaultValue = "0")int resultOffset) {

        try{
            Page<Team> teams = teamRepository.findAll(new PageRequest(resultOffset,numResults));
            return new TeamAll(0, teams.getContent());
        }
        catch (Exception exception){
            return new TeamAll(2);
        }
    }

    @RequestMapping(value = "/team/details", method = RequestMethod.GET)
    public TeamDetails getDetails(@RequestParam(value = "team_id") Long team_id) {
        try {
            Team team = teamRepository.findOne(team_id);
            return new TeamDetails(0, team);
        } catch (Exception exception) {
            return new TeamDetails(0);
        }
    }

    @RequestMapping(value = "/team/member/apply", method = RequestMethod.POST)
    public HashMap<String, String> applyMember(HttpServletRequest request,
                                               @RequestParam(value = "team_id")Long team_id) {
        try {
            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);
            Team team = teamRepository.findOne(team_id);
            if (Objects.equals(user.getId(), team.getCaptain().getId())
                    || team.getCurrentCount() >= team.getMaxCount()) {
                return Utils.getStateMessage("2");
            }
            TeamState teamState = teamStateRepository.getByDescription(Config.TEAM_STATE_APPLY);

            TeamUser teamUser = new TeamUser();
            teamUser.setMember(user);
            teamUser.setTeam(team);
            teamUser.setState(teamState);
            teamUserRepository.save(teamUser);

            return Utils.getStateMessage("0");
        } catch (Exception exception) {
            return Utils.getStateMessage("2");
        }
    }

    @RequestMapping(value = "team/member/dealWithApply",method = RequestMethod.POST)
    public HashMap<String,String> dealWithApply(HttpServletRequest request,
                                                @RequestParam(value = "team_id")Long teamId,
                                                @RequestParam(value = "apply_user_id")Long applyUserId,
                                                @RequestParam(value = "state")boolean state){
        try {
            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);
            Team team = teamRepository.findOne(teamId);
            if (Objects.equals(user.getId(), applyUserId)
                    || !Objects.equals(user.getId(), team.getCaptain().getId())) {
                return Utils.getStateMessage("2");
            }
            User applyUser = userRepository.findOne(applyUserId);

            TeamState teamState;
            if (state) {
                teamState = teamStateRepository.getByDescription(Config.TEAM_STATE_INTEAM);
            } else {
                teamState = teamStateRepository.getByDescription(Config.TEAM_STATE_APPLYFAIL);
            }
            TeamUser teamUser = teamUserRepository.findByMemberAndTeam(applyUser, team);
            teamUser.setState(teamState);
            teamUserRepository.save(teamUser);
            return Utils.getStateMessage("0");
        } catch (Exception exception) {
            return Utils.getStateMessage("2");
        }

    }

    @RequestMapping(value = "/team/member/invite", method = RequestMethod.POST)
    public HashMap<String, String> inviteMember(HttpServletRequest request,
                                               @RequestParam(value = "team_id")Long teamId,
                                                @RequestParam(value = "invite_user_id")Long inviteUserId) {
        try {
            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);
            Team team = teamRepository.findOne(teamId);
            if (!Objects.equals(user.getId(), team.getCaptain().getId())
                    || team.getCurrentCount() >= team.getMaxCount()
                    || Objects.equals(user.getId(), inviteUserId)) {
                return Utils.getStateMessage("2");
            }
            User invite_user = userRepository.findOne(inviteUserId);
            TeamState teamState = teamStateRepository.getByDescription(Config.TEAM_STATE_INVITE);

            TeamUser teamUser = new TeamUser();
            teamUser.setMember(invite_user);
            teamUser.setTeam(team);
            teamUser.setState(teamState);
            teamUserRepository.save(teamUser);

            return Utils.getStateMessage("0");
        } catch (Exception exception) {
            return Utils.getStateMessage("2");
        }
    }

    @RequestMapping(value = "/team/member/dealWithInvite",method = RequestMethod.POST)
    public HashMap<String,String> dealWithInvite(HttpServletRequest request,
                                                @RequestParam(value = "team_id")Long teamId,
                                                @RequestParam(value = "state")boolean state){
        try {
            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);
            Team team = teamRepository.findOne(teamId);
            if (Objects.equals(user.getId(), team.getCaptain().getId())) {
                return Utils.getStateMessage("2");
            }
            TeamState teamState;
            if (state) {
                teamState = teamStateRepository.getByDescription(Config.TEAM_STATE_INTEAM);
            } else {
                teamState = teamStateRepository.getByDescription(Config.TEAM_STATE_INVITEFAIL);
            }
            TeamUser teamUser = teamUserRepository.findByMemberAndTeam(user, team);
            teamUser.setState(teamState);
            teamUserRepository.save(teamUser);
            return Utils.getStateMessage("0");
        } catch (Exception exception) {
            return Utils.getStateMessage("2");
        }

    }

    @RequestMapping(value = "/team/delete", method = RequestMethod.POST)
    public HashMap<String, String> delete(HttpServletRequest request,
                                          @RequestParam(value = "team_id")Long teamId) {
        try {
            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);
            Team team = teamRepository.findOne(teamId);
            if(!Objects.equals(user.getId(), team.getCaptain().getId())){
                return Utils.getStateMessage("2");
            }
            teamRepository.delete(team);
            return Utils.getStateMessage("0");

        } catch (Exception exception) {
            return Utils.getStateMessage("2");
        }
    }

    @RequestMapping(value = "/team/search")
    public TeamAll search(@RequestParam(value = "tag_desc",defaultValue = "") String tagDesc,
                                    @RequestParam(value = "username",defaultValue = "") String username,
                                    @RequestParam(value = "key",defaultValue = "")String key,
                                    @RequestParam(value = "numResults",defaultValue = "20")int numResults,
                                    @RequestParam(value = "resultOffset", defaultValue = "0")int resultOffset) {
        return new TeamAll(0,teamRepository.findTeamsByTagDescription(new PageRequest(resultOffset,numResults), tagDesc,username,key));
    }




    @RequestMapping(value = "/team/test")
    public HashMap<String,TeamState> getTeamState() {
        HashMap<String,TeamState> teamStates = new HashMap<>();
        teamStates.put("1",teamStateRepository.getByDescription("apply"));
        teamStates.put("2",teamStateRepository.getOne(1L));
        return teamStates;
    }
}
