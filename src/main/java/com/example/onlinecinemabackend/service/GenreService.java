package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface GenreService extends EntityService<Genre, UUID> {
    Genre findByName(String name);

    Page<Genre> findAllByName(String name, Pageable pageable);
    boolean existsByName(String name);
}
