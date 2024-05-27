package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Subscription;
import com.example.onlinecinemabackend.repository.DirectorRepository;
import com.example.onlinecinemabackend.repository.SubscriptionRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.service.SubscriptionService;

import java.util.UUID;

public class SubscriptionServiceImpl extends AbstractEntityService<Subscription, UUID, SubscriptionRepository> implements SubscriptionService {


    public SubscriptionServiceImpl(SubscriptionRepository repository) {
        super(repository);
    }

    @Override
    protected Subscription updateFields(Subscription oldEntity, Subscription newEntity) {
        if (newEntity.getCost() != null){
            oldEntity.setCost(newEntity.getCost());
        }
        if (newEntity.getStart_date() != null){
            oldEntity.setStart_date(newEntity.getStart_date());
        }
        if (newEntity.getEnd_date() != null){
            oldEntity.setEnd_date(newEntity.getEnd_date());
        }
        return oldEntity;
    }
}
