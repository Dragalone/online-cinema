package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Season;
import com.example.onlinecinemabackend.entity.Series;
import com.example.onlinecinemabackend.web.model.request.UpsertSeasonRequest;
import com.example.onlinecinemabackend.web.model.request.UpsertSeriesRequest;
import com.example.onlinecinemabackend.web.model.response.SeasonResponse;
import com.example.onlinecinemabackend.web.model.response.SeriesResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(SeriesMapperDelegate.class)
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SeriesMapper {

    Series upsertRequestToSeries(UpsertSeriesRequest request);

   SeriesResponse seriesToResponse(Series series);

}
