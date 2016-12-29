package com.gc.controller;


import com.gc.Utils.Utils;
import com.gc.ViewModel.project.ProjectDetails;
import com.gc.model.Project;
import com.gc.model.User;
import com.gc.repository.repository.ProjectRepository;
import com.gc.repository.repository.ProjectStatusRepository;
import com.gc.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/admin/project")
@RestController
public class AdminProjectController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    private ProjectDetails manage(
            HttpServletRequest request,
            @RequestParam(value = "project_id")Long project_id,
            @RequestParam(value = "accept")boolean accept) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            Project project = projectRepository.findOne(project_id);

            // Project not found
            if(project == null) return  new ProjectDetails(ProjectDetails.PROJECT_NOT_FOUND);

            // User has no authority
            if(!Utils.checkAdmin(user)) return new ProjectDetails(ProjectDetails.NO_AUTHORITY);

            if (accept) {
                accept(project);
            }
            else {
                refuse(project);
            }

            projectRepository.save(project);

            // Modify successfully
            return new ProjectDetails(ProjectDetails.SUCCESS, project);
        }
        catch (Exception e) {
            // Extra error
            return new ProjectDetails(ProjectDetails.ERROR);
        }
    }


    private void accept(Project project) {
        if(project.getProject_status().getId() == 1L || project.getProject_status().getId() == 2L) {
            project.setProject_status(projectStatusRepository.findOne(3L));
        }
        else if(project.getProject_status().getId() == 3L || project.getProject_status().getId() == 4L) {
            project.setProject_status(projectStatusRepository.findOne(5L));
        }
        else if(project.getProject_status().getId() == 5L || project.getProject_status().getId() == 7L) {
            project.setProject_status(projectStatusRepository.findOne(10L));
        }
        else if(project.getProject_status().getId() == 6L) {
            project.setProject_status(projectStatusRepository.findOne(8L));
        }
        else if(project.getProject_status().getId() == 8L || project.getProject_status().getId() == 10L) {
            project.setProject_status(projectStatusRepository.findOne(11L));
        }
    }

    private void refuse(Project project) {
        if(project.getProject_status().getId() == 1L) {
            project.setProject_status(projectStatusRepository.findOne(2L));
        }
        else if(project.getProject_status().getId() == 3L) {
            project.setProject_status(projectStatusRepository.findOne(4L));
        }
        else if(project.getProject_status().getId() == 6L) {
            project.setProject_status(projectStatusRepository.findOne(7L));
        }
        else if(project.getProject_status().getId() == 5L) {
            project.setProject_status(projectStatusRepository.findOne(9L));
        }
    }
}
