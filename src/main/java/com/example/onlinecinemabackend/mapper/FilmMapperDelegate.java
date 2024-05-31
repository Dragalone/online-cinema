package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.web.dto.response.BriefFilmResponse;
import com.example.onlinecinemabackend.web.dto.response.FilmResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class FilmMapperDelegate  implements FilmMapper{


    @Autowired
    private FilmMapper delegate;

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private RatingMapper ratingMapper;
    @Override
    public BriefFilmResponse filmToBriefResponse(Film film){
        BriefFilmResponse response = delegate.filmToBriefResponse(film);
        response.setGenresCount(film.getGenres().size());
        response.setActorsCount(film.getActors().size());
        response.setRatingsCount(film.getRatings().size());
        return response;
    }

    @Override
    public FilmResponse filmToResponse(Film film){
        FilmResponse response = delegate.filmToResponse(film);
        response.setDirector(directorMapper.directorToResponse(film.getDirector()));
        response.setGenres(film.getGenres().stream()
                .map(it->genreMapper.genreToResponse(it))
                .toList());
        response.setActors(film.getActors().stream()
                .map(it->actorMapper.actorToResponse(it))
                .toList());
        response.setRatings(film.getRatings().stream()
                .map(it->ratingMapper.ratingToResponse(it))
                .toList());
        return response;
    }


}
