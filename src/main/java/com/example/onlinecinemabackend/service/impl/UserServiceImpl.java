package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Series;
import com.example.onlinecinemabackend.entity.User;
import com.example.onlinecinemabackend.exception.AlreadyExistsException;
import com.example.onlinecinemabackend.exception.EntityNotFoundException;
import com.example.onlinecinemabackend.repository.DirectorRepository;
import com.example.onlinecinemabackend.repository.UserRepository;
import com.example.onlinecinemabackend.service.AbstractEntityService;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl extends AbstractEntityService<User, UUID, UserRepository> implements UserService {
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected User updateFields(User oldEntity, User newEntity) {
        if (!Objects.equals(oldEntity.getName(), newEntity.getName())) {
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
        if (StringUtils.hasText(newEntity.getProfileImageURL())){
            oldEntity.setProfileImageURL(newEntity.getProfileImageURL());
        }
        return oldEntity;
    }

    @Override
    public User findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("User with name {0} not found!",name)));
    }
    @Override
    public Page<User> findAllByName(String name, Pageable pageable) {
        return repository.findAllByName(name, pageable);
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

    @Override
    public User save(User entity) {
        if (repository.existsByEmail(entity.getEmail())){
            throw new AlreadyExistsException(
                    MessageFormat.format("User with email {0} already exists!",  entity.getEmail())
            );
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.save(entity);
    }
}
