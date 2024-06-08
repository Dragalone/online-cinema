package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.RoleType;
import com.example.onlinecinemabackend.entity.User;
import com.example.onlinecinemabackend.exception.AlreadyExistsException;
import com.example.onlinecinemabackend.mapper.UserMapper;

import com.example.onlinecinemabackend.service.UserService;
import com.example.onlinecinemabackend.web.dto.request.PaginationRequest;

import com.example.onlinecinemabackend.web.dto.request.UpsertUserRequest;
import com.example.onlinecinemabackend.web.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
