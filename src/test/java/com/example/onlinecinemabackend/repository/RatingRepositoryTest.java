package com.example.onlinecinemabackend.repository;


import com.example.onlinecinemabackend.entity.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
@SpringBootTest
public class RatingRepositoryTest {
    @Autowired
    private RatingRepository ratingRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @Test
    public void whenFindAvgRating_thenReturnAvgRating(){
        double avgRating = ratingRepository.averageFilmRating(UUID.fromString("659cdb4d-c174-40d6-a5e3-eecc46bed3cf"));
        assertEquals(avgRating, 5.0);
    }


}
