package com.thisara.ProjectManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String category;

    //@ElementCollection this it will generate separate table for tags
    private List<String> tags= new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL,orphanRemoval = true)//here since mappedby we not create variable inside project insted in project
    private Chat chat;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)//here since mappedby we not create variable inside project
    private List<Issue> issues = new ArrayList<>();

    @ManyToMany
    private List<User> team = new ArrayList<>();  //invite members
}
