package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Episode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EpisodeRepository extends JpaRepository<Episode, UUID> {
    Page<Episode> findAllByTitle(String title, Pageable pageable);
    Optional<Episode> findByTitle(String title);

    boolean existsByTitle(String title);
}
