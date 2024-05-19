package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {

    Page<Genre> findAllByName(String name, Pageable pageable);

    Optional<Genre> findByName(String name);

    boolean existsByName(String name);
}
