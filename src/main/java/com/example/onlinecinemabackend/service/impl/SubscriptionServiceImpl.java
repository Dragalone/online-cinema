package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Subscription;
import com.example.onlinecinemabackend.entity.User;
import com.example.onlinecinemabackend.repository.DirectorRepository;
import com.example.onlinecinemabackend.repository.SubscriptionRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.service.SubscriptionService;
import com.example.onlinecinemabackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
@Slf4j
public class SubscriptionServiceImpl extends AbstractEntityService<Subscription, UUID, SubscriptionRepository> implements SubscriptionService {


    private final UserService userService;

    public SubscriptionServiceImpl(SubscriptionRepository repository,@Lazy UserService userService) {
        super(repository);
        this.userService = userService;
    }

    @Override
    protected Subscription updateFields(Subscription oldEntity, Subscription newEntity) {
        if (newEntity.getCost() != null){
            oldEntity.setCost(newEntity.getCost());
        }
        if (newEntity.getStartDate() != null){
            oldEntity.setStartDate(newEntity.getStartDate());
        }
        if (newEntity.getEndDate() != null){
            oldEntity.setEndDate(newEntity.getEndDate());
        }
        return oldEntity;
    }
    @Transactional
    @Override
    public Subscription addSubscription(Subscription subscription, UUID userId) {
        User user = userService.findById(userId);
        user.setSubscription(subscription);
        return save(subscription);
    }
    @Transactional
    @Override
    public Subscription updateSubscription(Subscription subscription, UUID id, UUID userId) {
        if (userId != null){
            User user = userService.findById(userId);
            user.setSubscription(subscription);
        }
        return update(id, subscription);
    }
}
