package com.example.onlinecinemabackend.repository;


import com.example.onlinecinemabackend.entity.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID> {
    Page<Season> findAllByTitle(String title, Pageable pageable);
    Optional<Season> findByTitle(String title);

    boolean existsByTitle(String title);
}
