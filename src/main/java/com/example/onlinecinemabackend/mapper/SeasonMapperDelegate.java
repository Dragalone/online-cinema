package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Season;

import com.example.onlinecinemabackend.web.model.response.SeasonResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SeasonMapperDelegate implements SeasonMapper {

    @Autowired
    private SeasonMapper delegate;

    @Autowired
    private EpisodeMapper episodeMapper;


    @Override
    public SeasonResponse seasonToResponse(Season season) {
        SeasonResponse response = delegate.seasonToResponse(season);
        response.setEpisodes(season.getEpisodes().stream()
                .map(it -> episodeMapper.episodeToResponse(it))
                .toList());
        return response;
    }
}
