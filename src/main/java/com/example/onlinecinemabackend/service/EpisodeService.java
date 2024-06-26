package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.entity.Episode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EpisodeService extends EntityService<Episode, UUID> {

    Page<Episode> findAllByTitle(String title, Pageable pageable);

    Episode findByTitle(String title);

    Episode addEpisode(Episode episode, UUID seasonId);

    Episode updateEpisode(Episode episode, UUID id, UUID seasonId);

    boolean existsByTitle(String title);

}
