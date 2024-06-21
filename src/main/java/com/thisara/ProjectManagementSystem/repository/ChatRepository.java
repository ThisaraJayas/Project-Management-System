package com.thisara.ProjectManagementSystem.repository;

import com.thisara.ProjectManagementSystem.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
