package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface ActorRepository extends JpaRepository<Actor, UUID> {

    Page<Actor> findAllByName(String name, Pageable pageable);

    Optional<Actor> findByName(String name);
}
