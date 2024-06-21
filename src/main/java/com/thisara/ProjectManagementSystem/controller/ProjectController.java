package com.thisara.ProjectManagementSystem.controller;

import com.thisara.ProjectManagementSystem.entity.Project;
import com.thisara.ProjectManagementSystem.entity.User;
import com.thisara.ProjectManagementSystem.service.ProjectService;
import com.thisara.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Project>>getProjects(@RequestParam(required = false)String category,
                                                    @RequestParam(required = false)String tag,
                                                    @RequestHeader("Authorization")String jwt) throws Exception {

        User user=userService.findUserProfileByJwt(jwt);
        List<Project>projects=projectService.getProjectByTeam(user,category,tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/projectId")
    public ResponseEntity<Project>getProjectById(@PathVariable Long projectId,
                                                    @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Project projects=projectService.getProjectById(projectId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
