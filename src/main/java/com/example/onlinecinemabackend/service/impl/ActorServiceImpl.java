package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Actor;
import com.example.onlinecinemabackend.repository.ActorRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActorServiceImpl extends AbstractEntityService<Actor, UUID, ActorRepository> implements ActorService {


    public ActorServiceImpl(ActorRepository repository) {
        super(repository);
    }

    @Override
    protected Actor updateFields(Actor oldEntity, Actor newEntity) {
        return null;
    }

    @Override
    public Actor addActor(Actor actor, List<UUID> filmIds, List<UUID> seriesIds) {
        return null;
    }
}
