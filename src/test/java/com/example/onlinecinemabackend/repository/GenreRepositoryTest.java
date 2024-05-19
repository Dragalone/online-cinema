package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Genre;
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
public class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @Test
    public void givenNewGenre_whenSave_thenSuccess(){
        Genre genre = new Genre();
        genre.setName("Thriller");
        Genre insertedGenre = genreRepository.save(genre);
        assertEquals(genre.getName(), insertedGenre.getName());
    }

    @Test
    public void whenFindById_thenReturnGenre(){
        assertFalse(genreRepository.findById(UUID.fromString("37bb059c-4d18-4caa-9ddf-152ba8dfab8b"))
                .isEmpty());
    }

    @Test
    public void whenFindByName_thenReturnGenre(){
        assertFalse(genreRepository.findByName("Name1")
                .isEmpty());
    }
}
