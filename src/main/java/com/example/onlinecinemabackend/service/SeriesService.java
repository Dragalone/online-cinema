package com.example.onlinecinemabackend.service;


import com.example.onlinecinemabackend.entity.Series;

import com.example.onlinecinemabackend.web.model.request.SeriesFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public interface SeriesService extends EntityService<Series, UUID> {

    Page<Series> filterBy(SeriesFilterRequest filter);
    Page<Series> findAllByTitle(String title, Pageable pageable);

    Series findByTitle(String title);

//    Page<Series> findAllByActors_IdIn(Collection<UUID> actors_id, Pageable pageable);
//    Page<Series> findAllByGenres_IdIn(Collection<UUID> genres_id, Pageable pageable);
//
//    Page<Series> findAllByDirectorId(UUID directorId, Pageable pageable);

    boolean existsByTitle(String title);

}
