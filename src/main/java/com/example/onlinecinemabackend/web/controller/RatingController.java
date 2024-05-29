package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.Rating;
import com.example.onlinecinemabackend.mapper.RatingMapper;
import com.example.onlinecinemabackend.service.RatingService;
import com.example.onlinecinemabackend.web.model.request.PaginationRequest;

import com.example.onlinecinemabackend.web.model.response.ModelListResponse;
import com.example.onlinecinemabackend.web.model.response.RatingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/rating",produces = "application/json")
public class RatingController {

    private final RatingService ratingService;

    private final RatingMapper ratingMapper;

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                ratingMapper.ratingToResponse(ratingService.findById(id))
        );
    }

    @GetMapping
    public ResponseEntity<ModelListResponse<RatingResponse>> findAllRatings(@Valid PaginationRequest request){
        Page<Rating> ratings = ratingService.findAll(request.pageRequest());

        return  ResponseEntity.ok(
                ModelListResponse.<RatingResponse>builder()
                        .totalCount(ratings.getTotalElements())
                        .data(ratings.stream().map(ratingMapper::ratingToResponse).toList())
                        .build()
        );
    }

}
