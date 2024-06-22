package com.thisara.ProjectManagementSystem.repository;

import com.thisara.ProjectManagementSystem.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue,Long> {
    public List<Issue> findByProjectId(Long id);
}
