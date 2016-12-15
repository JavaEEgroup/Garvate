package com.gc.controller;

import com.gc.Utils.Utils;
import com.gc.ViewModel.project.*;
import com.gc.model.*;
import com.gc.model.Fund;
import com.gc.model.ProjectStatus;
import com.gc.model.ProjectType;
import com.gc.repository.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private FundRepository fundRepository;

    @RequestMapping(value = "/all")
    private ProjectAll all(HttpServletRequest request) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            ProjectAll projectAll = new ProjectAll(ProjectAll.SUCCESS);
            projectAll.add2ProjectList(user.getAllProject());

            return projectAll;
        }
        catch (Exception e) {
            return new ProjectAll(ProjectAll.ERROR);
        }
    }

    @RequestMapping(value = "/details")
    private ProjectDetails details(
            HttpServletRequest request,
            @RequestParam(value = "id")Long id) {

        try {

            Project project = projectRepository.getOne(id);

            // Can not find the project
            if(project == null) return new ProjectDetails(ProjectDetails.PROJECT_NOT_FOUND);

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            // Check if user has authority
            if(!project.hasUser(user)) return new ProjectDetails(ProjectDetails.NO_AUTHORITY);

            ProjectDetails projectDetails = new ProjectDetails(ProjectDetails.SUCCESS, project);
            projectDetails.setUser_id(user.getId());
            projectDetails.setUsername(user.getUsername());

            return projectDetails;
        }
        catch (Exception exception) {
            // Extra error
            return new ProjectDetails(ProjectDetails.ERROR);
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

            // Team not found
            if(team == null) return  new ProjectAdd(ProjectAdd.TEAM_NOT_FOUND);

            // User has no authority
            if(!team.getCaptain().getId().equals(user.getId())) return new ProjectAdd(ProjectAdd.NO_AUTHORITY);

            // Already has project
            if(team.getProject() != null) return new ProjectAdd(ProjectAdd.ALREADY_HAS_PROJECT);


            ProjectType projectType = projectTypeRepository.getOne(project_type_id);
            ProjectStatus projectStatus = projectStatusRepository.getOne(1L);
            Project project = new Project(name, description, note, projectType, projectStatus, Utils.getCurrentTime());
            projectRepository.save(project);

            team.setProject(project);
            teamRepository.save(team);

            // Create successfully
            return new ProjectAdd(ProjectAdd.SUCCESS, project.getId());
        }
        catch (Exception e) {
            // Extra error
            return new ProjectAdd(ProjectAdd.ERROR);
        }
    }


    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    private ProjectDetails modify(
            HttpServletRequest request,
            @RequestParam(value = "project_id")Long project_id,
            @RequestParam(value = "name", defaultValue = "!@#")String name,
            @RequestParam(value = "description", defaultValue = "!@#")String description,
            @RequestParam(value = "note", defaultValue = "!@#")String note) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            Project project = projectRepository.findOne(project_id);

            // Project not found
            if(project == null) return  new ProjectDetails(ProjectDetails.PROJECT_NOT_FOUND);

            // User has no authority
            if(!project.hasUser(user)) return new ProjectDetails(ProjectDetails.NO_AUTHORITY);

            // Can not modify the project
            if(!project.modifiable()) return new ProjectDetails(ProjectDetails.NOT_MODIFIABLE);

            if(!name.equals("!@#")) project.setName(name);
            if(!description.equals("!@#")) project.setDescription(description);
            if(!note.equals("!@#")) project.setNote(note);
            projectRepository.save(project);

            // Modify successfully
            return new ProjectDetails(ProjectDetails.SUCCESS, project);
        }
        catch (Exception e) {
            // Extra error
            return new ProjectDetails(ProjectDetails.ERROR);
        }
    }

    @RequestMapping(value = "/modify_note", method = RequestMethod.POST)
    private ProjectDetails modify_note(
            HttpServletRequest request,
            @RequestParam(value = "project_id")Long project_id,
            @RequestParam(value = "note")String note) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            Project project = projectRepository.findOne(project_id);

            // Project not found
            if(project == null) return  new ProjectDetails(ProjectDetails.PROJECT_NOT_FOUND);

            // User has no authority
            if(!project.hasUser(user)) return new ProjectDetails(ProjectDetails.NO_AUTHORITY);

            project.setNote(note);
            projectRepository.save(project);

            // Modify successfully
            return new ProjectDetails(ProjectDetails.SUCCESS, project);
        }
        catch (Exception e) {
            // Extra error
            return new ProjectDetails(ProjectDetails.ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private ProjectDelete delete(
            HttpServletRequest request,
            @RequestParam(value = "project_id")Long project_id) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            Project project = projectRepository.findOne(project_id);

            // Project not found
            if(project == null) return  new ProjectDelete(ProjectDelete.PROJECT_NOT_FOUND);

            // User has no authority
            if(!project.getTeam().getCaptain().getId().equals(user.getId())) return new ProjectDelete(ProjectDelete.NO_AUTHORITY);

            // Can not modify the project
            if(!project.modifiable()) return new ProjectDelete(ProjectDelete.NOT_MODIFIABLE);

            Team team = project.getTeam();
            team.setProject(null);
            teamRepository.save(team);

            projectRepository.delete(project_id);

            // Modify successfully
            return new ProjectDelete(ProjectDelete.SUCCESS);
        }
        catch (Exception e) {
            // Extra error
            return new ProjectDelete(ProjectDelete.ERROR);
        }
    }

    @RequestMapping(value = "/fund/add", method = RequestMethod.POST)
    private FundAdd addFund(
            HttpServletRequest request,
            @RequestParam(value = "project_id")Long project_id,
            @RequestParam(value = "fund_type")String fund_type,
            @RequestParam(value = "value")Long value,
            @RequestParam(value = "note", defaultValue = "")String note) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            Project project = projectRepository.findOne(project_id);

            // Project not found
            if(project == null) return  new FundAdd(FundAdd.PROJECT_NOT_FOUND);

            // User has no authority
            if(!project.hasUser(user)) return new FundAdd(FundAdd.NO_AUTHORITY);

            Fund fund = new Fund(fund_type, note, value, project);
            fundRepository.save(fund);

            return new FundAdd(FundAdd.SUCCESS);
        }
        catch (Exception e) {
            return new FundAdd(FundAdd.ERROR);
        }
    }

    @RequestMapping(value = "/fund/modify", method = RequestMethod.POST)
    private FundModify modifyFund(
            HttpServletRequest request,
            @RequestParam(value = "fund_id")Long fund_id,
            @RequestParam(value = "fund_type", defaultValue = "!@#")String fund_type,
            @RequestParam(value = "value", defaultValue = "-1")Long value,
            @RequestParam(value = "note", defaultValue = "!@#")String note) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            Fund fund = fundRepository.findOne(fund_id);

            // Fund not found
            if(fund == null) return  new FundModify(FundModify.FUND_NOT_FOUND);

            // User has no authority
            if(!fund.getProject().hasUser(user)) return new FundModify(FundModify.NO_AUTHORITY);

            if(!fund_type.equals("!@#")) fund.setFund_type(fund_type);
            if(!value.equals(-1)) fund.setValue(value);
            if(!note.equals("!@#")) fund.setNote(note);
            fund.setEdit_time(Utils.getCurrentTime());
            fundRepository.save(fund);

            return new FundModify(FundModify.SUCCESS);
        }
        catch (Exception e) {
            return new FundModify(FundModify.ERROR);
        }
    }

    @RequestMapping(value = "/fund/delete", method = RequestMethod.POST)
    private FundDelete deleteFund(
            HttpServletRequest request,
            @RequestParam(value = "fund_id")Long fund_id) {

        try {

            String user_account = request.getRemoteUser();
            User user = userRepository.findByAccount(user_account);

            Fund fund = fundRepository.findOne(fund_id);

            // Fund not found
            if(fund == null) return  new FundDelete(FundDelete.FUND_NOT_FOUND);

            // User has no authority
            if(!fund.getProject().hasUser(user)) return new FundDelete(FundDelete.NO_AUTHORITY);

            fundRepository.delete(fund);

            return new FundDelete(FundDelete.SUCCESS);
        }
        catch (Exception e) {
            return new FundDelete(FundDelete.ERROR);
        }
    }
}

