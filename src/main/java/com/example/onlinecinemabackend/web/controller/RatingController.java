package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.Rating;
import com.example.onlinecinemabackend.mapper.RatingMapper;
import com.example.onlinecinemabackend.service.RatingService;
import com.example.onlinecinemabackend.web.dto.request.PaginationRequest;

import com.example.onlinecinemabackend.web.dto.response.ModelListResponse;
import com.example.onlinecinemabackend.web.dto.response.RatingResponse;
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

    @GetMapping("/user-id")
    public ResponseEntity<ModelListResponse<RatingResponse>> findAllByUser_Id(@Valid PaginationRequest request,@RequestParam UUID userId){
        Page<Rating> ratings = ratingService.findAllByUser_Id(userId, request.pageRequest());
        return  ResponseEntity.ok(
                ModelListResponse.<RatingResponse>builder()
                        .totalCount(ratings.getTotalElements())
                        .data(ratings.stream().map(ratingMapper::ratingToResponse).toList())
                        .build()
        );
    }
    @GetMapping("/series-id")
    public ResponseEntity<ModelListResponse<RatingResponse>> findAllBySeries_Id(@Valid PaginationRequest request,@RequestParam UUID seriesId){
        Page<Rating> ratings = ratingService.findAllBySeries_Id(seriesId, request.pageRequest());
        return  ResponseEntity.ok(
                ModelListResponse.<RatingResponse>builder()
                        .totalCount(ratings.getTotalElements())
                        .data(ratings.stream().map(ratingMapper::ratingToResponse).toList())
                        .build()
        );
    }

    @GetMapping("/film-id")
    public ResponseEntity<ModelListResponse<RatingResponse>> findAllByFilm_Id(@Valid PaginationRequest request,@RequestParam UUID filmId){
        Page<Rating> ratings = ratingService.findAllByFilm_Id(filmId, request.pageRequest());
        return  ResponseEntity.ok(
                ModelListResponse.<RatingResponse>builder()
                        .totalCount(ratings.getTotalElements())
                        .data(ratings.stream().map(ratingMapper::ratingToResponse).toList())
                        .build()
        );
    }
}
