package com.example.onlinecinemabackend.service;


import com.example.onlinecinemabackend.entity.Series;

import com.example.onlinecinemabackend.web.dto.request.SeriesFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

public interface SeriesService extends EntityService<Series, UUID> {

    Page<Series> filterBy(SeriesFilterRequest filter);
    Page<Series> findAllByTitle(String title, Pageable pageable);

    Series addSeries(Series series, List<UUID> actorsIds, List<UUID> genresIds, UUID directorId);

    Series updateSeries(Series series,UUID id, List<UUID> actorsIds, List<UUID> genresIds, UUID directorId);

    Series findByTitle(String title);

    boolean existsByTitle(String title);

}
