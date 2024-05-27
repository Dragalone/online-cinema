package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Series;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.DirectorRepository;
import com.example.onlinecinemabackend.repository.SeriesRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.service.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.UUID;
@Service
@Slf4j
public class SeriesServiceImpl extends AbstractEntityService<Series, UUID, SeriesRepository> implements SeriesService {
    public SeriesServiceImpl(SeriesRepository repository) {
        super(repository);
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
        if (newEntity.getRelease_date() != null){
            oldEntity.setRelease_date(newEntity.getRelease_date());
        }
        return oldEntity;
    }

    @Override
    public Page<Series> findAllByTitle(String title, Pageable pageable) {
        return repository.findAllByTitle(title, pageable);
    }

    @Override
    public Series findByTitle(String title) {
        return repository.findByTitle(title)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("Series with title {0} not found!",title)));
    }

    @Override
    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }
}
