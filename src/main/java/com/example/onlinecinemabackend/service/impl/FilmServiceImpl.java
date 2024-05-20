package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.repository.FilmRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.FilmService;

import java.util.UUID;

public class FilmServiceImpl extends AbstractEntityService<Film, UUID, FilmRepository> implements FilmService {
    public FilmServiceImpl(FilmRepository repository) {
        super(repository);
    }

    @Override
    protected Film updateFields(Film oldEntity, Film newEntity) {
        return null;
    }
}
