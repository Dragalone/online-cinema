package com.example.onlinecinemabackend.web.controller;

import com.example.onlinecinemabackend.entity.Series;
import com.example.onlinecinemabackend.entity.User;
import com.example.onlinecinemabackend.mapper.SeriesMapper;
import com.example.onlinecinemabackend.mapper.UserMapper;
import com.example.onlinecinemabackend.service.SeriesService;
import com.example.onlinecinemabackend.service.UserService;
import com.example.onlinecinemabackend.web.model.request.PaginationRequest;
import com.example.onlinecinemabackend.web.model.response.ModelListResponse;
import com.example.onlinecinemabackend.web.model.response.SeriesResponse;
import com.example.onlinecinemabackend.web.model.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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


}
