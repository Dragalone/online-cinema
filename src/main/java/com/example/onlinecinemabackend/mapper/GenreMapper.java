package com.example.onlinecinemabackend.mapper;


import com.example.onlinecinemabackend.entity.Genre;

import com.example.onlinecinemabackend.web.dto.request.UpsertGenreRequest;

import com.example.onlinecinemabackend.web.dto.response.GenreResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface GenreMapper {
    Genre upsertRequestToGenre(UpsertGenreRequest request);

    GenreResponse genreToResponse(Genre genre);
}
