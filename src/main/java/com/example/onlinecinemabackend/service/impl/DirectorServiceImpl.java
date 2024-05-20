package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.repository.DirectorRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.DirectorService;

import java.util.UUID;

public class DirectorServiceImpl extends AbstractEntityService<Director, UUID, DirectorRepository> implements DirectorService {
    public DirectorServiceImpl(DirectorRepository repository) {
        super(repository);
    }

    @Override
    protected Director updateFields(Director oldEntity, Director newEntity) {
        return null;
    }
}
