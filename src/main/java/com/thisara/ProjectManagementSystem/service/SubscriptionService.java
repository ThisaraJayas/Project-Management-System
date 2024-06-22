package com.thisara.ProjectManagementSystem.service;

import com.thisara.ProjectManagementSystem.entity.PlanType;
import com.thisara.ProjectManagementSystem.entity.Subscription;
import com.thisara.ProjectManagementSystem.entity.User;

public interface SubscriptionService {
    Subscription createSubscription(User user);
    Subscription getUserSubscription(Long userId) throws Exception;
    Subscription upgradeSubscription(Long userId, PlanType planType);
    boolean isValid(Subscription subscription);
}
