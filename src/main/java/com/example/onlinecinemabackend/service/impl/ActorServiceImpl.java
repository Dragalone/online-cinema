package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Actor;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.ActorRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.ActorService;
import org.aspectj.bridge.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
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

    @Override
    public Page<Actor> findAllByName(String name, Pageable pageable) {
        return repository.findAllByName(name, pageable);
    }

    @Override
    public Actor findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(()-> new EntityNotFoundException(
                        MessageFormat.format("Actor with name {0} not found!", name)
                        ));
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
