package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Actor;
import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.entity.Genre;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.FilmRepository;
import com.example.onlinecinemabackend.repository.FilmSpecification;
import com.example.onlinecinemabackend.service.*;
import com.example.onlinecinemabackend.web.dto.request.FilmFilterRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.*;

@Service
@Slf4j
public class FilmServiceImpl extends AbstractEntityService<Film, UUID, FilmRepository> implements FilmService {

    public final ActorService actorService;
    public final GenreService genreService;

    public final DirectorService directorService;

    public FilmServiceImpl(FilmRepository repository, ActorService actorService, GenreService genreService, DirectorService directorService) {
        super(repository);
        this.actorService = actorService;
        this.genreService = genreService;
        this.directorService = directorService;
    }

    @Override
    protected Film updateFields(Film oldEntity, Film newEntity) {
        if (StringUtils.hasText(newEntity.getTitle())){
            oldEntity.setTitle(newEntity.getTitle());
        }
        if (StringUtils.hasText(newEntity.getDescription())){
            oldEntity.setDescription(newEntity.getDescription());
        }
        if (newEntity.getActors()!=null){
            oldEntity.setActors(newEntity.getActors());
        }
        if (newEntity.getCost() != null){
            oldEntity.setCost(newEntity.getCost());
        }
        if (newEntity.getDirector() != null){
            oldEntity.setDirector(newEntity.getDirector());
        }
        if (newEntity.getGenres() != null){
            oldEntity.setGenres(newEntity.getGenres());
        }
        if (newEntity.getRatings() != null){
            oldEntity.setRatings(newEntity.getRatings());
        }
        if (StringUtils.hasText(newEntity.getResourceLink())){
            oldEntity.setResourceLink(newEntity.getResourceLink());
        }
        if (newEntity.getReleaseDate() != null){
            oldEntity.setReleaseDate(newEntity.getReleaseDate());
        }
        return oldEntity;
    }


    @Override
    public Film findByTitle(String title) {
        return repository.findByTitle(title)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("Film with title {0} not found!",title)));
    }

    @Override
    public Page<Film> findAllByTitle(String title, Pageable pageable) {
        return repository.findAllByTitle(title,pageable);
    }

    @Override
    @Transactional
    public Film updateFilm(Film film, UUID id, List<UUID> actorsIds, List<UUID> genresIds, UUID directorId) {
        Set<Actor> actors = new HashSet<>();
        Set<Genre> genres = new HashSet<>();
        if (actorsIds != null){
            for (var actorId : actorsIds){
                actors.add(actorService.findById(actorId));
            }
        }
        film.setActors(actors);
        if (genresIds != null){
            for (var genreId : genresIds){
                genres.add(genreService.findById(genreId));
            }
        }
        film.setGenres(genres);
        if (directorId != null){
            film.setDirector(directorService.findById(directorId));
        }
        return update(id, film);
    }

    @Override
    public Page<Film> filterBy(FilmFilterRequest filter) {
        return repository.findAll(
                FilmSpecification.withFilter(filter),
                filter.getPagination().pageRequest()
        );
    }

    //    @Override
//    @Transactional
//    public Actor addActor(Actor actor, UUID filmId, UUID seriesId) {
//        Film film = filmService.findById(filmId);
//        film.addActor(actor);
//        return save(actor);
//    }
    @Override
    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }
}
