package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Rating;
import com.example.onlinecinemabackend.web.dto.request.UpsertRatingRequest;
import com.example.onlinecinemabackend.web.dto.response.RatingResponse;
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
