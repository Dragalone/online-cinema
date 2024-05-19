package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Actor;
import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface FilmRepository extends JpaRepository<Film, UUID> {

    Page<Film> findAllByTitle(String title, Pageable pageable);
    Optional<Film> findByTitle(String title);

    Page<Film> findAllByActors_IdIn(Collection<UUID> actors_id, Pageable pageable);
    Page<Film> findAllByGenres_IdIn(Collection<UUID> genres_id, Pageable pageable);

    Page<Film> findAllByDirectorId(UUID directorId, Pageable pageable);

    boolean existsByTitle(String title);


}
