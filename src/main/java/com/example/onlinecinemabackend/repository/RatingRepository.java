package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Genre;
import com.example.onlinecinemabackend.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {

    Page<Rating> findAllByRating(Integer rating, Pageable pageable);

    Page<Rating> findAllByUser_Id(UUID userId, Pageable pageable);

    Page<Rating> findAllByFilm_Id(UUID filmId, Pageable pageable);

    Page<Rating> findAllBySeries_Id(UUID seriesId, Pageable pageable);

    @Query("SELECT AVG(r.rating) FROM rating r WHERE r.film.id = ?1")
    Double averageFilmRating(UUID filmId);
    @Query("SELECT AVG(r.rating) FROM rating r WHERE r.series.id = ?1")
    Double averageSeriesRating(UUID seriesId);
}
