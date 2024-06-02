package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.entity.Genre;
import com.example.onlinecinemabackend.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RatingService extends EntityService<Rating, UUID> {

    Page<Rating> findAllByRating(Integer rating, Pageable pageable);

    Page<Rating> findAllByUser_Id(UUID userId, Pageable pageable);

    Page<Rating> findAllByFilm_Id(UUID filmId, Pageable pageable);

    Page<Rating> findAllBySeries_Id(UUID seriesId, Pageable pageable);
    Rating addRating(Rating rating, UUID userId, UUID filmId, UUID seriesId);

    Double averageFilmRating(UUID filmId);

    Double averageSeriesRating(UUID seriesId);
}