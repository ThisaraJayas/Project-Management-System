package com.thisara.ProjectManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String status;  //in progress donne, ..
    private Long projectId;
    private String priority;
    private LocalDate dueDate;
    private List<String> tags=new ArrayList<>();

    @ManyToOne  //many issue has one user
    private User assignee;

    @JsonIgnore
    @ManyToOne
    private Project project;

    @JsonIgnore //hanlde recurssion problem
    @OneToMany(mappedBy = "issue",cascade = CascadeType.ALL,orphanRemoval = true) //mapby dont create separate table go to issue and create nside
    private List<Comment>comments= new ArrayList<>();
}
