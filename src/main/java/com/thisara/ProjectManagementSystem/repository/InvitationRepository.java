package com.thisara.ProjectManagementSystem.repository;

import com.thisara.ProjectManagementSystem.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Invitation findByToken(String token);
    Invitation findByEmail(String userEmail);
}
