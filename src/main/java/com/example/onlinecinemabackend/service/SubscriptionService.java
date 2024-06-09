package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.entity.Subscription;

import java.util.UUID;

public interface SubscriptionService extends EntityService<Subscription, UUID> {
    Subscription addSubscription(Subscription subscription, UUID userId);

    Subscription updateSubscription(Subscription subscription,UUID id, UUID userId);
}
