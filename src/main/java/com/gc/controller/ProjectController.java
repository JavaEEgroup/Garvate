package com.gc.controller;

import com.gc.Utils.Utils;
import com.gc.ViewModel.project.ProjectAdd;
import com.gc.ViewModel.project.ProjectAll;
import com.gc.ViewModel.project.ProjectDetails;
import com.gc.model.*;
import com.gc.repository.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProjectTypeRepository projectTypeRepository;

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @RequestMapping(value = "/all")
    private ProjectAll all(HttpServletRequest request) {

        try {

            ArrayList<Project> projects = new ArrayList<>();

            ProjectAll projectAll = new ProjectAll(0);

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            // 没有判断在TeamUserStage
            for(TeamUser teamUser : user.getTeamUserList()) {
                Project userProject = teamUser.getTeam().getProject();
                if(userProject != null){
                    projects.add(userProject);
                }
            }

            projectAll.add2ProjectList(projects);

            return projectAll;
        }
        catch (Exception e) {
            return new ProjectAll(1);
        }
    }

    @RequestMapping(value = "/details")
    private ProjectDetails details(
            HttpServletRequest request,
            @RequestParam(value = "id")Long id) {

        try {

            Project project = projectRepository.getOne(id);

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            // 似乎需要验证访问权限？？？
//            boolean not_fount = true;
//            for(User x : project.getUserList()) {
//                if (Objects.equals(x.getId(), user.getId())){
//                    not_fount = false;
//                    break;
//                }
//            }
//            if(not_fount) return new ProjectDetails(2);

            ProjectDetails projectDetails = new ProjectDetails(project);
            projectDetails.setUser_id(user.getId());
            projectDetails.setUsername(user.getUsername());

            return projectDetails;
        }
        catch (Exception exception) {
            return new ProjectDetails(1);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private ProjectAdd add(
            HttpServletRequest request,
            @RequestParam(value = "team_id")Long team_id,
            @RequestParam(value = "name")String name,
            @RequestParam(value = "description")String description,
            @RequestParam(value = "note", defaultValue = "")String note,
            @RequestParam(value = "project_type_id")Long project_type_id) {


        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            Team team = teamRepository.getOne(team_id);

            // 5 -> team已有project
            if(team.getProject() != null) return new ProjectAdd(5);

            // 依然没有加判断 =  -=
            ProjectType projectType = projectTypeRepository.getOne(project_type_id);
            ProjectStatus projectStatus = projectStatusRepository.getOne(1L);
            Project project = new Project(name, description, note, projectType, projectStatus, Utils.getCurrentTime());
            projectRepository.save(project);

            team.setProject(project);
            teamRepository.save(team);

            return new ProjectAdd(0, project.getId());
        }
        catch (Exception e) {
            // 1 -> 崩了
            return new ProjectAdd(1);
        }
    }
}

