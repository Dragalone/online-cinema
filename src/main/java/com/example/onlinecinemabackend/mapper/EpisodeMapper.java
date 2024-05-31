package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Episode;

import com.example.onlinecinemabackend.web.dto.request.UpsertEpisodeRequest;

import com.example.onlinecinemabackend.web.dto.response.EpisodeResponse;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EpisodeMapper {

    Episode upsertRequestToEpisode(UpsertEpisodeRequest request);

    EpisodeResponse episodeToResponse(Episode episode);

}
