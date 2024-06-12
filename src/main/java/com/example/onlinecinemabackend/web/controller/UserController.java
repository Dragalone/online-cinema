package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.aop.AccessCheckType;
import com.example.onlinecinemabackend.aop.Accessible;
import com.example.onlinecinemabackend.entity.Episode;
import com.example.onlinecinemabackend.entity.RoleType;
import com.example.onlinecinemabackend.entity.User;
import com.example.onlinecinemabackend.exception.AlreadyExistsException;
import com.example.onlinecinemabackend.mapper.UserMapper;

import com.example.onlinecinemabackend.service.UserService;
import com.example.onlinecinemabackend.web.dto.request.PaginationRequest;

import com.example.onlinecinemabackend.web.dto.request.UpsertUserRequest;
import com.example.onlinecinemabackend.web.dto.response.EpisodeResponse;
import com.example.onlinecinemabackend.web.dto.response.ModelListResponse;
import com.example.onlinecinemabackend.web.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/api/v1/user",produces = "application/json")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                userMapper.userToResponse(userService.findById(id))
        );
    }
    @GetMapping("/name")
    public ResponseEntity<UserResponse> getByName(@Valid PaginationRequest request, @RequestParam String name){
        return  ResponseEntity.ok(
                userMapper.userToResponse(userService.findByName(name))
        );
    }

    @GetMapping("all/name")
    public ResponseEntity<ModelListResponse<UserResponse>> getAllByName(@Valid PaginationRequest request, @RequestParam String name){
        Page<User> users = userService.findAllByName(name, request.pageRequest());
        return  ResponseEntity.ok(
                ModelListResponse.<UserResponse>builder()
                        .totalCount(users.getTotalElements())
                        .data(users.stream().map(userMapper::userToResponse).toList())
                        .build()
        );
    }

    @GetMapping("/email")
    public ResponseEntity<UserResponse> getByEmail(@Valid PaginationRequest request, @RequestParam String email){
        return  ResponseEntity.ok(
                userMapper.userToResponse(userService.findByEmail(email))
        );
    }
    @GetMapping("exists-by/email")
    public ResponseEntity<Boolean> existsByEmail(@Valid PaginationRequest request, @RequestParam String email){
        return  ResponseEntity.ok(
                userService.existsByEmail(email)
        );
    }
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UpsertUserRequest request, @RequestParam RoleType role) {
        if (userService.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException(MessageFormat.format("User with email {0} already exists!", request.getEmail()));
        }
        if (userService.existsByName(request.getName())) {
            throw new AlreadyExistsException(MessageFormat.format("User with email {0} already exists!", request.getEmail()));
        }
        User newUser = userMapper.upsertRequestToUser(request);
        newUser.addRole(role);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(userService.save(newUser)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpsertUserRequest request, @PathVariable UUID id) {
        User updatedUser = userService.update(id, userMapper.upsertRequestToUser(request));
        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
