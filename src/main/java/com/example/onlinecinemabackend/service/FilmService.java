package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface FilmService extends EntityService<Film, UUID> {
    Film findByTitle(String title);

    Page<Film> findAllByTitle(String title, Pageable pageable);

    Film updateFilm(Film film, UUID id, List<UUID> actorsIds, List<UUID> genresIds, UUID directorId);

//    Page<Film> findAllByActors_IdIn(Collection<UUID> actors_id, Pageable pageable);
//    Page<Film> findAllByGenres_IdIn(Collection<UUID> genres_id, Pageable pageable);
//
//    Page<Film> findAllByDirectorId(UUID directorId, Pageable pageable);

    boolean existsByTitle(String title);
}
