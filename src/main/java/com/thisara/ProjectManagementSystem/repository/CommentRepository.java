package com.thisara.ProjectManagementSystem.repository;

import com.thisara.ProjectManagementSystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    List<Comment> findByIssueId(Long issueId);
}
