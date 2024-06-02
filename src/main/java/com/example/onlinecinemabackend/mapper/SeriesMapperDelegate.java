package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Series;
import com.example.onlinecinemabackend.repository.RatingRepository;
import com.example.onlinecinemabackend.service.RatingService;
import com.example.onlinecinemabackend.web.dto.response.SeriesResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SeriesMapperDelegate implements SeriesMapper{

    @Autowired
    private SeriesMapper delegate;

    @Autowired
    private SeasonMapper seasonMapper;

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private RatingMapper ratingMapper;

    @Autowired
    private RatingService ratingService;
    @Override
    public SeriesResponse seriesToResponse(Series series){
        SeriesResponse response = delegate.seriesToResponse(series);

        response.setDirector(directorMapper.directorToResponse(series.getDirector()));

        response.setGenres(series.getGenres().stream()
                .map(it->genreMapper.genreToResponse(it))
                .toList());

        response.setActors(series.getActors().stream()
                .map(it->actorMapper.actorToResponse(it))
                .toList());

        response.setRatings(series.getRatings().stream()
                .map(it->ratingMapper.ratingToResponse(it))
                .toList());

        response.setSeasons(series.getSeasons().stream()
                .map(it -> seasonMapper.seasonToResponse(it))
                .toList());
        response.setAverageRating(ratingService.averageSeriesRating(series.getId()));
        return response;
    }
}

