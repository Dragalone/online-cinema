package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.exception.AlreadyExistsException;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.DirectorRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.DirectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;
@Service
@Slf4j
public class DirectorServiceImpl extends AbstractEntityService<Director, UUID, DirectorRepository> implements DirectorService {
    public DirectorServiceImpl(DirectorRepository repository) {
        super(repository);
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

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
