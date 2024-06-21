package com.thisara.ProjectManagementSystem.service;

import com.thisara.ProjectManagementSystem.entity.Chat;
import com.thisara.ProjectManagementSystem.entity.Project;
import com.thisara.ProjectManagementSystem.entity.User;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project, User user) throws Exception;
    List<Project> getProjectByTeam(User user, String category, String tags) throws Exception;
    Project getProjectById(Long projectId)throws Exception;

    void deleteProject(Long projectId, Long userId) throws Exception;

    Project updatedProject(Project updatedProject, Long id)throws Exception;
    void addUserToProject(Long projectId, Long userId)throws Exception;
    void removeUserFromProject(Long projectId, Long userId)throws Exception;

    Chat getChatByProjectId(Long projectId) throws Exception;

    List<Project>searchProjects(String keyword, User user) throws Exception;
}
