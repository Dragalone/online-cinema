package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.entity.User;

import java.util.UUID;

public interface UserService extends EntityService<User, UUID> {

    User findByName(String name);

    User findByEmail(String email);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

}
