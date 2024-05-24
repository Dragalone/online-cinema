package com.example.onlinecinemabackend.web.controller;

import com.example.onlinecinemabackend.mapper.DirectorMapper;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.web.model.response.DirectorResponse;
import com.example.onlinecinemabackend.web.model.response.GenreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/director")
public class DirectorController {

    private final DirectorService directorService;

    private final DirectorMapper directorMapper;

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                directorMapper.directorToResponse(directorService.findById(id))
        );
    }
}
