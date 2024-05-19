package com.example.onlinecinemabackend.repository;


import com.example.onlinecinemabackend.entity.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {
    Page<Director> findAllById(UUID directorId, Pageable pageable);
    Page<Director> findAllByName(String name, Pageable pageable);

    Optional<Director> findByName(String name);

    boolean existsByName(String name);
}
