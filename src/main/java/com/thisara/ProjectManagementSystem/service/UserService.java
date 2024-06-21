package com.thisara.ProjectManagementSystem.service;

import com.thisara.ProjectManagementSystem.entity.User;

public interface UserService {

    User findUserProfileByJwt(String jwt) throws Exception;
    User findUserByEmail(String email)throws Exception;
    User findUserById(Long userId)throws Exception;
    User updateUserByProjectSize(User user, int number);
}
