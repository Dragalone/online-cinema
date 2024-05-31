package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Season;
import com.example.onlinecinemabackend.web.dto.request.UpsertSeasonRequest;
import com.example.onlinecinemabackend.web.dto.response.SeasonResponse;
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
