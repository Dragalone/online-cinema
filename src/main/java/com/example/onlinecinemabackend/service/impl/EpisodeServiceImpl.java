package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.*;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.DirectorRepository;
import com.example.onlinecinemabackend.repository.EpisodeRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.service.EpisodeService;
import com.example.onlinecinemabackend.service.SeasonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.UUID;
@Service
@Slf4j
public class EpisodeServiceImpl extends AbstractEntityService<Episode, UUID, EpisodeRepository> implements EpisodeService {

    private final SeasonService seasonService;
    public EpisodeServiceImpl(EpisodeRepository repository, SeasonService seasonService) {
        super(repository);
        this.seasonService = seasonService;
    }

    @Override
    protected Episode updateFields(Episode oldEntity, Episode newEntity) {
        if (StringUtils.hasText(newEntity.getTitle())){
            oldEntity.setTitle(newEntity.getTitle());
        }
        if (StringUtils.hasText(newEntity.getDescription())){
            oldEntity.setDescription(newEntity.getDescription());
        }
        if (newEntity.getResourceLink()!=null){
            oldEntity.setResourceLink(newEntity.getResourceLink());
        }
        if (newEntity.getSeason() != null){
            oldEntity.setSeason(newEntity.getSeason());
        }
        if (StringUtils.hasText(newEntity.getPreviewImageURL())){
            oldEntity.setPreviewImageURL(newEntity.getPreviewImageURL());
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
    @Transactional
    @Override
    public Episode addEpisode(Episode episode, UUID seasonId) {
        Season season = seasonService.findById(seasonId);
        season.addEpisode(episode);
        return save(episode);
    }
    @Transactional
    @Override
    public Episode updateEpisode(Episode episode, UUID id, UUID seasonId) {
        if (seasonId != null){
            Season season = seasonService.findById(seasonId);
            episode.setSeason(season);
        }
        return update(id, episode);
    }

    @Override
    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }


}
