package com.gc.controller;

import com.gc.ViewModel.project.ProjectAll;
import com.gc.ViewModel.project.ProjectDetails;
import com.gc.model.Project;
import com.gc.model.TeamUser;
import com.gc.model.User;
import com.gc.repository.repository.ProjectRepository;
import com.gc.repository.repository.TeamRepository;
import com.gc.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

