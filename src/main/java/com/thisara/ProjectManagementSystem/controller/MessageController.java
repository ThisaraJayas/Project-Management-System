package com.thisara.ProjectManagementSystem.controller;

import com.thisara.ProjectManagementSystem.entity.Chat;
import com.thisara.ProjectManagementSystem.entity.Message;
import com.thisara.ProjectManagementSystem.entity.User;
import com.thisara.ProjectManagementSystem.request.CreateMessageRequest;
import com.thisara.ProjectManagementSystem.service.MessageService;
import com.thisara.ProjectManagementSystem.service.ProjectService;
import com.thisara.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message>sendMessage(@RequestBody CreateMessageRequest request) throws Exception{
        User user = userService.findUserById(request.getSenderId());

        Chat chat = projectService.getProjectById(request.getProjectId()).getChat();
        if(chat==null){
            throw new Exception("Chat not found");
        }
        Message sentMessage = messageService.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());
        return ResponseEntity.ok(sentMessage);
    }
    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>>getMessageByChatId(@PathVariable Long projectId) throws Exception{
        List<Message>messages=messageService.getMessageByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
