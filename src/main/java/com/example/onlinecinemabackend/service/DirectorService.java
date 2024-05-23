package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.entity.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface  DirectorService extends EntityService<Director, UUID> {
    Director findByName(String name);

    Page<Director> findAllByName(String name, Pageable pageable);
    boolean existsByName(String name);
}
