package com.example.onlinecinemabackend.web.controller;

import com.example.onlinecinemabackend.mapper.GenreMapper;
import com.example.onlinecinemabackend.service.GenreService;
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
@RequestMapping("/api/v1/genre")
public class GenreController {

    private final GenreService genreService;

    private final GenreMapper genreMapper;

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                genreMapper.genreToResponse(genreService.findById(id))
        );
    }


}
