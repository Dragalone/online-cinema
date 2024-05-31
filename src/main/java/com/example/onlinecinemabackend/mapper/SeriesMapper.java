package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Series;
import com.example.onlinecinemabackend.web.dto.request.UpsertSeriesRequest;
import com.example.onlinecinemabackend.web.dto.response.SeriesResponse;
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
