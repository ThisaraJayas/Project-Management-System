package com.thisara.ProjectManagementSystem.service;

import com.thisara.ProjectManagementSystem.entity.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(Long senderId, Long chatId, String content) throws Exception;
    List<Message>getMessageByProjectId(Long projectId)throws Exception;
}
