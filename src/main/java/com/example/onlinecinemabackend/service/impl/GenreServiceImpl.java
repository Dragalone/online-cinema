package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Genre;
import com.example.onlinecinemabackend.repository.GenreRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenreServiceImpl extends AbstractEntityService<Genre, UUID, GenreRepository> implements GenreService {

    public GenreServiceImpl(GenreRepository repository) {
        super(repository);
    }

    @Override
    protected Genre updateFields(Genre oldEntity, Genre newEntity) {
        return null;
    }
}
