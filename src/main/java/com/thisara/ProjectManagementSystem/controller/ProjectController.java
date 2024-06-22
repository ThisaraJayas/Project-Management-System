package com.thisara.ProjectManagementSystem.controller;

import com.thisara.ProjectManagementSystem.entity.Chat;
import com.thisara.ProjectManagementSystem.entity.Invitation;
import com.thisara.ProjectManagementSystem.entity.Project;
import com.thisara.ProjectManagementSystem.entity.User;
import com.thisara.ProjectManagementSystem.repository.InviteRequest;
import com.thisara.ProjectManagementSystem.response.MessageResponse;
import com.thisara.ProjectManagementSystem.service.InvitationService;
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

    @Autowired
    private InvitationService invitationService;
    @GetMapping
    public ResponseEntity<List<Project>>getProjects(@RequestParam(required = false)String category,
                                                    @RequestParam(required = false)String tag,
                                                    @RequestHeader("Authorization")String jwt) throws Exception {

        User user=userService.findUserProfileByJwt(jwt);
        List<Project>projects=projectService.getProjectByTeam(user,category,tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project>getProjectById(@PathVariable Long projectId,
                                                    @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Project projects=projectService.getProjectById(projectId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project>createProject(
                                                 @RequestHeader("Authorization")String jwt,
                                                 @RequestBody Project project) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Project createdProjects=projectService.createProject(project,user);
        return new ResponseEntity<>(createdProjects, HttpStatus.OK);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project>updateProject(@PathVariable Long projectId,
                                                 @RequestHeader("Authorization")String jwt,
                                                 @RequestBody Project project) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Project updatedProject=projectService.updatedProject(project,projectId);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse>deleteProject(@PathVariable Long projectId,
                                                @RequestHeader("Authorization")String jwt
                                                ) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
       projectService.deleteProject(projectId,user.getId());
        MessageResponse res = new MessageResponse("Project deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>>searchProjects(@RequestParam(required = false)String keyword,

                                                    @RequestHeader("Authorization")String jwt) throws Exception {

        User user=userService.findUserProfileByJwt(jwt);
        List<Project>projects=projectService.searchProjects(keyword,user);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat>getChatProjectById(@PathVariable Long projectId,
                                                 @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Chat chat=projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/invite")
    public ResponseEntity<MessageResponse>inviteProject(
            @RequestBody InviteRequest req,
            @RequestHeader("Authorization")String jwt,
            @RequestBody Project project) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Project createdProjects=projectService.createProject(project,user);
        invitationService.sendInvitation(req.getEmail(), req.getProjectId());
        MessageResponse res = new MessageResponse("User Invitation send");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/accept_invitation")
    public ResponseEntity<Invitation>acceptInviteProject(
            @RequestParam String token,
            @RequestHeader("Authorization")String jwt,
            @RequestBody Project project) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Invitation invitation =invitationService.acceptInvitation(token,user.getId());
        projectService.addUserToProject(invitation.getProjectId(),user.getId());
        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
    }
}
