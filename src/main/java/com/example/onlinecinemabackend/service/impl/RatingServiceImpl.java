package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Rating;

import com.example.onlinecinemabackend.repository.RatingRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;

import com.example.onlinecinemabackend.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@Slf4j
public class RatingServiceImpl extends AbstractEntityService<Rating, UUID, RatingRepository> implements RatingService {

    public RatingServiceImpl(RatingRepository repository) {
        super(repository);
    }

    @Override
    protected Rating updateFields(Rating oldEntity, Rating newEntity) {
        if (StringUtils.hasText(newEntity.getComment())){
            oldEntity.setComment(newEntity.getComment());
        }
        if (newEntity.getRating() >= 1 && newEntity.getRating() <= 10){
            oldEntity.setRating(newEntity.getRating());
        }
        if (newEntity.getFilm()!=null){
            oldEntity.setFilm(newEntity.getFilm());
        }
        if (newEntity.getUser()!=null){
            oldEntity.setUser(newEntity.getUser());
        }
        if (newEntity.getSeries()!=null){
            oldEntity.setSeries(newEntity.getSeries());
        }
        return oldEntity;
    }

    @Override
    public Page<Rating> findAllByRating(Integer rating, Pageable pageable) {
        return repository.findAllByRating(rating,pageable);
    }

    @Override
    public Page<Rating> findAllByUser_Id(UUID userId, Pageable pageable) {
        return repository.findAllByUser_Id(userId,pageable);
    }

    @Override
    public Page<Rating> findAllByFilm_Id(UUID filmId, Pageable pageable) {
        return repository.findAllByFilm_Id(filmId,pageable);
    }

    @Override
    public Page<Rating> findAllBySeries_Id(UUID seriesId, Pageable pageable) {
        return repository.findAllBySeries_Id(seriesId,pageable);
    }
}
