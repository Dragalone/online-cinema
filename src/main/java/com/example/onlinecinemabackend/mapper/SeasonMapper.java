package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Genre;
import com.example.onlinecinemabackend.entity.Season;
import com.example.onlinecinemabackend.web.model.request.UpsertGenreRequest;
import com.example.onlinecinemabackend.web.model.request.UpsertSeasonRequest;
import com.example.onlinecinemabackend.web.model.response.GenreResponse;
import com.example.onlinecinemabackend.web.model.response.SeasonResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
@DecoratedWith(SeasonMapperDelegate.class)
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SeasonMapper {
    Season upsertRequestToSeason(UpsertSeasonRequest request);

    SeasonResponse seasonToResponse(Season season);
}
