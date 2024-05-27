package com.example.onlinecinemabackend.service;


import com.example.onlinecinemabackend.entity.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SeasonService extends EntityService<Season, UUID> {

    Page<Season> findAllByTitle(String title, Pageable pageable);

    Season findByTitle(String title);

    boolean existsByTitle(String title);

}
