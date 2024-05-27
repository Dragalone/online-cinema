package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface SeriesService extends EntityService<Series, UUID> {


    Page<Series> findAllByTitle(String title, Pageable pageable);

    Series findByTitle(String title);

//    Page<Series> findAllByActors_IdIn(Collection<UUID> actors_id, Pageable pageable);
//    Page<Series> findAllByGenres_IdIn(Collection<UUID> genres_id, Pageable pageable);
//
//    Page<Series> findAllByDirectorId(UUID directorId, Pageable pageable);

    boolean existsByTitle(String title);

}
