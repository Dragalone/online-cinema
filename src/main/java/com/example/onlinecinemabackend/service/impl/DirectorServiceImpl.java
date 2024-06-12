package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.entity.Series;
import com.example.onlinecinemabackend.exception.AlreadyExistsException;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.DirectorRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.service.FilmService;
import com.example.onlinecinemabackend.service.SeriesService;
import lombok.extern.slf4j.Slf4j;
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
public class DirectorServiceImpl extends AbstractEntityService<Director, UUID, DirectorRepository> implements DirectorService {


   private final FilmService filmService;

   private final SeriesService seriesService;


    public DirectorServiceImpl(DirectorRepository repository, @Lazy FilmService filmService,@Lazy SeriesService seriesService) {
        super(repository);
        this.filmService = filmService;
        this.seriesService = seriesService;
    }


    @Override
    protected Director updateFields(Director oldEntity, Director newEntity) {
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
        if (StringUtils.hasText(newEntity.getDirectorImageURL())){
            oldEntity.setDirectorImageURL(newEntity.getDirectorImageURL());
        }
        return oldEntity;
    }

    @Override
    public Director findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(()->
                new EntityNotFoundException(MessageFormat.format("Director with name {0} not found!",name)
                ));
    }

    @Override
    public Page<Director> findAllByName(String name, Pageable pageable) {
        return repository.findAllByName(name,pageable);
    }


    @Transactional
    @Override
    public Director addDirector(Director director, List<UUID> filmsIds, List<UUID> seriesIds) {
        List<Film> films = new ArrayList<>();
        for(var filmId : filmsIds){
            films.add(filmService.findById(filmId));
        }
        List<Series> series = new ArrayList<>();
        for(var seriesId : seriesIds){
            series.add(seriesService.findById(seriesId));
        }

        director.setFilms(films);
        director.setSeriesList(series);
        return save(director);
    }
    @Transactional
    @Override
    public Director updateDirector(Director director, UUID id, List<UUID> filmsIds, List<UUID> seriesIds) {

        if(filmsIds != null) {
            List<Film> films = new ArrayList<>();
            for (var filmId : filmsIds) {
                films.add(filmService.findById(filmId));
            }
            director.setFilms(films);
        }

        if (seriesIds != null) {
            List<Series> series = new ArrayList<>();
            for (var seriesId : seriesIds) {
                series.add(seriesService.findById(seriesId));
            }
            director.setSeriesList(series);
        }
        return update(id,director);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
