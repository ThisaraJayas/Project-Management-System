package com.thisara.ProjectManagementSystem.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
