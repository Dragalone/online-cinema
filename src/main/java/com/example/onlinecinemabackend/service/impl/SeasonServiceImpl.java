package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Season;
import com.example.onlinecinemabackend.entity.Series;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.DirectorRepository;
import com.example.onlinecinemabackend.repository.SeasonRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.service.SeasonService;
import com.example.onlinecinemabackend.service.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.UUID;
@Service
@Slf4j
public class SeasonServiceImpl extends AbstractEntityService<Season, UUID, SeasonRepository> implements SeasonService {

    private final SeriesService seriesService;

    public SeasonServiceImpl(SeasonRepository repository,@Lazy SeriesService seriesService) {
        super(repository);
        this.seriesService = seriesService;
    }

    @Override
    protected Season updateFields(Season oldEntity, Season newEntity) {
        if (StringUtils.hasText(newEntity.getTitle())){
            oldEntity.setTitle(newEntity.getTitle());
        }
        if (StringUtils.hasText(newEntity.getDescription())){
            oldEntity.setDescription(newEntity.getDescription());
        }
        if (newEntity.getEpisodes()!=null){
            oldEntity.setEpisodes(newEntity.getEpisodes());
        }
        if (newEntity.getSeries() != null){
            oldEntity.setSeries(newEntity.getSeries());
        }
        if (newEntity.getReleaseDate() != null){
            oldEntity.setReleaseDate(newEntity.getReleaseDate());
        }
        if (StringUtils.hasText(newEntity.getPreviewImageURL())){
            oldEntity.setPreviewImageURL(newEntity.getPreviewImageURL());
        }
        return oldEntity;
    }

    @Override
    public Page<Season> findAllByTitle(String title, Pageable pageable) {
        return repository.findAllByTitle(title, pageable);
    }

    @Override
    public Season findByTitle(String title) {
        return repository.findByTitle(title)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("Season with title {0} not found!",title)));
    }
    @Transactional
    @Override
    public Season addSeason(Season season, UUID seriesId) {
        Series series = seriesService.findById(seriesId);
        series.addSeason(season);
        return save(season);
    }
    @Transactional
    @Override
    public Season updateSeason(Season season, UUID id, UUID seriesId) {
        if (seriesId!=null){
            Series series = seriesService.findById(seriesId);
            season.setSeries(series);
        }
        return update(id, season);
    }

    @Override
    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }
}
