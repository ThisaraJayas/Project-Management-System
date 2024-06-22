package com.thisara.ProjectManagementSystem.service;

import com.thisara.ProjectManagementSystem.entity.Issue;
import com.thisara.ProjectManagementSystem.entity.User;
import com.thisara.ProjectManagementSystem.request.IssueRequest;
import jdk.jshell.spi.ExecutionControl;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;

}