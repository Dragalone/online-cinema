package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Genre;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.GenreRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.UUID;

@Service
@Slf4j
public class GenreServiceImpl extends AbstractEntityService<Genre, UUID, GenreRepository> implements GenreService {

    public GenreServiceImpl(GenreRepository repository) {
        super(repository);
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

    @Override
    public Page<Genre> findAllByName(String name, Pageable pageable) {
        return repository.findAllByName(name, pageable);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
