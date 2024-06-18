package com.thisara.ProjectManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne  //many issue has one user
    private User assignee;
}
