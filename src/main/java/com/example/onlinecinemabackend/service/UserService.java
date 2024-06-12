package com.example.onlinecinemabackend.service;


import com.example.onlinecinemabackend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService extends EntityService<User, UUID> {

    User findByName(String name);

    Page<User> findAllByName(String name, Pageable pageable);

    User findByEmail(String email);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

}
