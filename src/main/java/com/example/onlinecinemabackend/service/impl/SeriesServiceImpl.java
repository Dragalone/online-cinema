package com.example.onlinecinemabackend.service.impl;


import com.example.onlinecinemabackend.entity.*;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;

import com.example.onlinecinemabackend.repository.SeriesSpecification;
import com.example.onlinecinemabackend.repository.SeriesRepository;
import com.example.onlinecinemabackend.service.*;

import com.example.onlinecinemabackend.web.dto.request.SeriesFilterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Service
@Slf4j
public class SeriesServiceImpl extends AbstractEntityService<Series, UUID, SeriesRepository> implements SeriesService {

    public final ActorService actorService;
    public final GenreService genreService;

    public final DirectorService directorService;
    public SeriesServiceImpl(SeriesRepository repository, @Lazy ActorService actorService,@Lazy GenreService genreService,@Lazy DirectorService directorService) {
        super(repository);
        this.actorService = actorService;
        this.genreService = genreService;
        this.directorService = directorService;
    }

    @Override
    protected Series updateFields(Series oldEntity, Series newEntity) {
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
        if (newEntity.getReleaseDate() != null){
            oldEntity.setReleaseDate(newEntity.getReleaseDate());
        }
        if (StringUtils.hasText(newEntity.getPreviewImageURL())){
            oldEntity.setPreviewImageURL(newEntity.getPreviewImageURL());
        }
        return oldEntity;
    }

    @Override
    public Page<Series> findAllByTitle(String title, Pageable pageable) {
        return repository.findAllByTitle(title, pageable);
    }

    @Transactional
    @Override
    public Series addSeries(Series series, List<UUID> actorsIds, List<UUID> genresIds, UUID directorId) {
        Set<Actor> actors = new HashSet<>();
        for(var actorId : actorsIds){
            actors.add(actorService.findById(actorId));
        }
        Set<Genre> genres = new HashSet<>();
        for(var genreId : genresIds){
            genres.add(genreService.findById(genreId));
        }
        Director director = directorService.findById(directorId);
        series.setActors(actors);
        series.setGenres(genres);
        series.setDirector(director);
        return save(series);
    }
    @Transactional
    @Override
    public Series updateSeries(Series series, UUID id, List<UUID> actorsIds, List<UUID> genresIds, UUID directorId) {
       if (actorsIds != null){
           Set<Actor> actors = new HashSet<>();
           for(var actorId : actorsIds){
               actors.add(actorService.findById(actorId));
           }
           series.setActors(actors);
       }
       if (genresIds != null){
           Set<Genre> genres = new HashSet<>();
           for(var genreId : genresIds){
               genres.add(genreService.findById(genreId));
           }
           series.setGenres(genres);
       }

       if (directorId != null){
           Director director = directorService.findById(directorId);
           series.setDirector(director);
       }
        return update(id, series);
    }


    @Override
    public Series findByTitle(String title) {
        return repository.findByTitle(title)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("Series with title {0} not found!",title)));
    }
    @Override
    public Page<Series> filterBy(SeriesFilterRequest filter) {
        return repository.findAll(
                SeriesSpecification.withFilter(filter),
                filter.getPagination().pageRequest()
        );
    }
    @Override
    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }
}
