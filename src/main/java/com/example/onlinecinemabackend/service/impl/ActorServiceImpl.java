package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.*;
import com.example.onlinecinemabackend.exception.AlreadyExistsException;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.ActorRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.ActorService;
import com.example.onlinecinemabackend.service.FilmService;
import com.example.onlinecinemabackend.service.SeriesService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.*;

@Service
@Slf4j
public class ActorServiceImpl extends AbstractEntityService<Actor, UUID, ActorRepository> implements ActorService {



    private final SeriesService seriesService;
    private final FilmService filmService;

    public ActorServiceImpl(ActorRepository repository, @Lazy SeriesService seriesService, @Lazy FilmService filmService) {
        super(repository);
        this.seriesService = seriesService;
        this.filmService = filmService;
    }





   // private final SeriesService SeriesService;
    @Override
    protected Actor updateFields(Actor oldEntity, Actor newEntity) {
        if (StringUtils.hasText(newEntity.getName())){
            oldEntity.setName(newEntity.getName());
        }
        if (StringUtils.hasText(newEntity.getInformation())){
            oldEntity.setInformation(newEntity.getInformation());
        }
        if (newEntity.getSeriesList()!=null){
            oldEntity.setSeriesList(newEntity.getSeriesList());
        }
        if (newEntity.getFilms()!=null){
            oldEntity.setFilms(newEntity.getFilms());
        }
        if (newEntity.getBirthdate() != null){
            oldEntity.setBirthdate(newEntity.getBirthdate());
        }
        return oldEntity;
    }

    @Override
    @Transactional
    public Actor addActor(Actor actor, List<UUID> filmsIds, List<UUID> seriesIds) {
        Set<Film> films = new HashSet<>();
        for(var filmId : filmsIds){
            films.add(filmService.findById(filmId));
        }
        Set<Series> series = new HashSet<>();
        for(var seriesId : seriesIds){
            series.add(seriesService.findById(seriesId));
        }

        actor.setFilms(films);
        actor.setSeriesList(series);
        return save(actor);
    }

    @Transactional
    @Override
    public Actor updateActor(Actor actor, UUID id, List<UUID> filmsIds, List<UUID> seriesIds) {
        if (filmsIds != null){
            Set<Film> films = new HashSet<>();
            for(var filmId : filmsIds){
                films.add(filmService.findById(filmId));
            }
            actor.setFilms(films);
        }
        if (seriesIds != null){
            Set<Series> series = new HashSet<>();
            for(var seriesId : seriesIds){
                series.add(seriesService.findById(seriesId));
            }
            actor.setSeriesList(series);
        }
        return update(id,actor);
    }

    @Override
    public Page<Actor> findAllByName(String name, Pageable pageable) {
        return repository.findAllByName(name, pageable);
    }

    @Override
    public Actor findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(()-> new EntityNotFoundException(
                        MessageFormat.format("Actor with name {0} not found!", name)
                        ));
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
