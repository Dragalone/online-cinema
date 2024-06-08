package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.User;
import com.example.onlinecinemabackend.exception.AlreadyExistsException;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.DirectorRepository;
import com.example.onlinecinemabackend.repository.UserRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl extends AbstractEntityService<User, UUID, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    protected User updateFields(User oldEntity, User newEntity) {
        if (!Objects.equals(oldEntity.getName(), newEntity.getName()) && existsByName(newEntity.getName())) {
            throw new AlreadyExistsException(
                    MessageFormat.format("User with username {0} already exists!",  newEntity.getName())
            );
        } else if (!Objects.equals(oldEntity.getName(), newEntity.getName())) {
            oldEntity.setName(newEntity.getName());
        }

        if (!Objects.equals(oldEntity.getEmail(), newEntity.getEmail()) && existsByEmail(newEntity.getEmail())) {
            throw new AlreadyExistsException(
                    MessageFormat.format("User with email {0} already exists!",  newEntity.getEmail())
            );
        } else if (!Objects.equals(oldEntity.getEmail(), newEntity.getEmail())) {
            oldEntity.setEmail(newEntity.getEmail());
        }
         if (newEntity.getPhone() != null){
             oldEntity.setPhone(newEntity.getPhone());
         }
        return oldEntity;
    }

    @Override
    public User findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("User with name {0} not found!",name)));
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("User with email {0} not found!",email)));
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
