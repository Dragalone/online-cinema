package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface SeriesRepository extends JpaRepository<Series, UUID>, JpaSpecificationExecutor<Series> {

    Page<Series> findAllByTitle(String title, Pageable pageable);
    Optional<Series> findByTitle(String title);


    Page<Series> findAllByActors_IdIn(Collection<UUID> actors_id, Pageable pageable);
    Page<Series> findAllByGenres_IdIn(Collection<UUID> genres_id, Pageable pageable);

    Page<Series> findAllByDirectorId(UUID directorId, Pageable pageable);

    boolean existsByTitle(String title);

}
