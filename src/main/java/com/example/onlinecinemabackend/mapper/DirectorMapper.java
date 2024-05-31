package com.example.onlinecinemabackend.mapper;


import com.example.onlinecinemabackend.entity.Director;

import com.example.onlinecinemabackend.web.dto.request.UpsertDirectorRequest;

import com.example.onlinecinemabackend.web.dto.response.DirectorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DirectorMapper {
    Director upsertRequestToDirector(UpsertDirectorRequest request);

    DirectorResponse directorToResponse(Director director);
}
