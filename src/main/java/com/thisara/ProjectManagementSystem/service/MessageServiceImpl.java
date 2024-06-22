package com.thisara.ProjectManagementSystem.service;

import com.thisara.ProjectManagementSystem.entity.Chat;
import com.thisara.ProjectManagementSystem.entity.Message;
import com.thisara.ProjectManagementSystem.entity.User;
import com.thisara.ProjectManagementSystem.repository.MessageRepository;
import com.thisara.ProjectManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public Message sendMessage(Long senderId, Long chatId, String content) throws Exception {
        User sender= userRepository.findById(senderId)
                .orElseThrow(()->new Exception("User not found with id "+senderId));
        Chat chat = projectService.getProjectById(chatId).getChat();

        Message message = new Message();
        message.setContent(content);
        message.setSender(sender);
        message.setCreatedAt(LocalDateTime.now());
        message.setChat(chat);
        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);

        return savedMessage;
    }

    @Override
    public List<Message> getMessageByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        List<Message>findByChatIdOrderByCreatedAtAsc= messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
        return findByChatIdOrderByCreatedAtAsc;
    }
}
