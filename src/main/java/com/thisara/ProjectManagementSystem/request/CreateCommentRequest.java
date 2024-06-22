package com.thisara.ProjectManagementSystem.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {
    private Long issueId;
    private String content;
}
