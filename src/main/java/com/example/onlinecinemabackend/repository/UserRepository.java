package com.example.onlinecinemabackend.repository;


import com.example.onlinecinemabackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    boolean existsByName (String name);

    boolean existsByEmail(String email);

}
