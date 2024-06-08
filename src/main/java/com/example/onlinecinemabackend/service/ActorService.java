package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.entity.Actor;


import com.example.onlinecinemabackend.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActorService extends EntityService<Actor, UUID> {


    Page<Actor> findAllByName(String name, Pageable pageable);

    Actor findByName(String name);

    boolean existsByName(String name);

    public Actor addActor(Actor actor, List<UUID> filmsIds, List<UUID> seriesIds);
}
