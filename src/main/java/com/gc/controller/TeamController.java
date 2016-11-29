package com.gc.controller;

import com.gc.Utils.Utils;
import com.gc.model.Tag;
import com.gc.model.Team;
import com.gc.model.User;
import com.gc.repository.TagRespository;
import com.gc.repository.TeamRepository;
import com.gc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRespository tagRespository;

    @RequestMapping(value = "/team/add",method = RequestMethod.POST)
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

            List<Tag> tags = new ArrayList<>();
            for (Long tagID : tagIDs) {
                Tag tag = tagRespository.findOne(tagID);
                tags.add(tag);
            }
            team.setTagList(tags);

            teamRepository.save(team);

            HashMap<String, String> results = Utils.getStateMessage("0");
            results.put("team_id", "" + team.getId());
            return results;
        } catch (Exception exception) {
            return Utils.getStateMessage("2");
        }
    }
}
