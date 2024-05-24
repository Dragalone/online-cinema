package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Actor;
import com.example.onlinecinemabackend.web.model.request.UpsertActorRequest;
import com.example.onlinecinemabackend.web.model.response.ActorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ActorMapper {
    Actor upsertRequestToActor(UpsertActorRequest request);

    ActorResponse actorToResponse(Actor actor);
}