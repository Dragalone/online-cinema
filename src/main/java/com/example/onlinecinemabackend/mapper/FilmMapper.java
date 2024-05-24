package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.web.model.request.UpsertFilmRequest;
import com.example.onlinecinemabackend.web.model.response.BriefFilmResponse;
import com.example.onlinecinemabackend.web.model.response.FilmResponse;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@DecoratedWith(FilmMapperDelegate.class)
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FilmMapper {
    Film upsertRequestToFilm(UpsertFilmRequest request);

    @Mapping(source = "director.name", target = "directorName")
    BriefFilmResponse filmToBriefResponse(Film film);


    FilmResponse filmToResponse(Film film);
}
