package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.entity.Genre;
import com.example.onlinecinemabackend.entity.Series;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.GenreRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.FilmService;
import com.example.onlinecinemabackend.service.GenreService;
import com.example.onlinecinemabackend.service.SeriesService;
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
public class GenreServiceImpl extends AbstractEntityService<Genre, UUID, GenreRepository> implements GenreService {

    private final FilmService filmService;

    private final SeriesService seriesService;


    public GenreServiceImpl(GenreRepository repository, @Lazy FilmService filmService,@Lazy SeriesService seriesService) {
        super(repository);
        this.filmService = filmService;
        this.seriesService = seriesService;
    }

    @Override
    protected Genre updateFields(Genre oldEntity, Genre newEntity) {
        if (StringUtils.hasText(newEntity.getName())){
            oldEntity.setName(newEntity.getName());
        }
        if (newEntity.getSeriesList()!=null){
            oldEntity.setSeriesList(newEntity.getSeriesList());
        }
        if (newEntity.getFilms()!=null){
            oldEntity.setFilms(newEntity.getFilms());
        }
        return oldEntity;
    }

    @Override
    public Genre findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(
                        () -> new EntityNotFoundException(MessageFormat.format("Genre with name {0} not found!",name)));
    }

    @Transactional
    @Override
    public Genre addGenre(Genre genre, List<UUID> filmIds, List<UUID> seriesIds) {
        Set<Film> films = new HashSet<>();
        for(var filmId : filmIds){
            films.add(filmService.findById(filmId));
        }
        Set<Series> series = new HashSet<>();
        for(var seriesId : seriesIds){
            series.add(seriesService.findById(seriesId));
        }
        genre.setFilms(films);
        genre.setSeriesList(series);
        return save(genre);
    }
    @Transactional
    @Override
    public Genre updateGenre(Genre genre, UUID id, List<UUID> filmIds, List<UUID> seriesIds) {
        if (filmIds != null) {
            Set<Film> films = new HashSet<>();
            for (var filmId : filmIds) {
                films.add(filmService.findById(filmId));
            }
            genre.setFilms(films);
        }
        if (seriesIds != null) {
            Set<Series> series = new HashSet<>();
            for (var seriesId : seriesIds) {
                series.add(seriesService.findById(seriesId));
            }
            genre.setSeriesList(series);
        }
        return update(id, genre);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
