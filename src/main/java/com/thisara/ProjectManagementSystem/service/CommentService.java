package com.thisara.ProjectManagementSystem.service;

import com.thisara.ProjectManagementSystem.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long issueId, Long userId, String comment) throws Exception;
    void deleteComment(Long commentId, Long userId) throws Exception;
    List<Comment>findCommentByIssueId(Long issueId);
}
