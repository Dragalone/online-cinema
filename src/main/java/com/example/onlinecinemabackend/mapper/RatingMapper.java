package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Genre;
import com.example.onlinecinemabackend.entity.Rating;
import com.example.onlinecinemabackend.web.model.request.UpsertGenreRequest;
import com.example.onlinecinemabackend.web.model.request.UpsertRatingRequest;
import com.example.onlinecinemabackend.web.model.response.GenreResponse;
import com.example.onlinecinemabackend.web.model.response.RatingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RatingMapper {

    Rating upsertRequestToRating(UpsertRatingRequest request);

    RatingResponse ratingToResponse(Rating rating);

}
