package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.entity.Actor;

import java.util.List;
import java.util.UUID;

public interface ActorService extends EntityService<Actor, UUID> {
    Actor addActor(Actor actor, List<UUID> filmIds, List<UUID> seriesIds);


}
