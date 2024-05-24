package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Actor;
import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.exception.AlreadyExistsException;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.ActorRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.ActorService;
import com.example.onlinecinemabackend.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ActorServiceImpl extends AbstractEntityService<Actor, UUID, ActorRepository> implements ActorService {



    //private final SeriesService seriesService;
    public ActorServiceImpl(ActorRepository repository) {
        super(repository);
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
