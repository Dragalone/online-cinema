package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Episode;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.DirectorRepository;
import com.example.onlinecinemabackend.repository.EpisodeRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.service.EpisodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.UUID;
@Service
@Slf4j
public class EpisodeServiceImpl extends AbstractEntityService<Episode, UUID, EpisodeRepository> implements EpisodeService {
    public EpisodeServiceImpl(EpisodeRepository repository) {
        super(repository);
    }

    @Override
    protected Episode updateFields(Episode oldEntity, Episode newEntity) {
        if (StringUtils.hasText(newEntity.getTitle())){
            oldEntity.setTitle(newEntity.getTitle());
        }
        if (StringUtils.hasText(newEntity.getDescription())){
            oldEntity.setDescription(newEntity.getDescription());
        }
        if (newEntity.getResource_link()!=null){
            oldEntity.setResource_link(newEntity.getResource_link());
        }
        if (newEntity.getSeason() != null){
            oldEntity.setSeason(newEntity.getSeason());
        }
        return oldEntity;
    }

    @Override
    public Page<Episode> findAllByTitle(String title, Pageable pageable) {
        return repository.findAllByTitle(title,pageable);
    }

    @Override
    public Episode findByTitle(String title) {
        return repository.findByTitle(title)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("Episode with title {0} not found!",title)));
    }

    @Override
    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }
}
