package com.thisara.ProjectManagementSystem.repository;

import com.thisara.ProjectManagementSystem.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

    Subscription findByUserId(Long userId);
}
