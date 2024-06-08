package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GenreService extends EntityService<Genre, UUID> {
    Genre findByName(String name);

    Genre addGenre(Genre genre, List<UUID> filmIds, List<UUID> seriesIds);

    boolean existsByName(String name);
}
