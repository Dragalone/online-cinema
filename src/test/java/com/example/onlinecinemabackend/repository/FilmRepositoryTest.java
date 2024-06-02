package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Film;

import com.example.onlinecinemabackend.web.dto.request.FilmFilterRequest;
import com.example.onlinecinemabackend.web.dto.request.PaginationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

import org.springframework.data.domain.PageRequest;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
public class FilmRepositoryTest {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");



    @Test
    public void givenNewFilm_whenSave_thenSuccess(){
        Film film = new Film();
        film.setCost(BigDecimal.valueOf(123.00));
        film.setTitle("Test Film");
        Film insertedFilm = filmRepository.save(film);
        assertEquals(film.getTitle(), insertedFilm.getTitle());
    }

    @Test
    public void whenFindAllByDirectorId_thenReturnFilms(){
        Director director = directorRepository.findByName("Name1").orElse(null);
        assertFalse(filmRepository
                .findAllByDirectorId(director.getId(), PageRequest.of(0,2))
                .getContent()
                .isEmpty());
    }

    @Test
    public void whenFindAllByTitle_thenReturnFilms(){
        assertFalse(filmRepository
                .findAllByTitle("Title1", PageRequest.of(0,2))
                .getContent()
                .isEmpty());
    }

    @Test
    public void whenFindByTitle_thenReturnFilm(){
        assertFalse(filmRepository
                .findByTitle("Title1").isEmpty());
    }

    @Test
    public void whenFindAllByActors_IdIn_thenReturnFilms(){
        List<UUID> actorsIds = new ArrayList<>();
        actorsIds.add(actorRepository.findByName("Name1").orElse(null).getId());
        actorsIds.add(actorRepository.findByName("Name2").orElse(null).getId());
        assertFalse(filmRepository.
                findAllByActors_IdIn(actorsIds,PageRequest.of(0,2))
                .getContent()
                .isEmpty()
        );
    }

    @Test
    public void whenFindAllByGenres_IdIn_thenReturnFilms(){
        List<UUID> genresIds = new ArrayList<>();
        genresIds.add(genreRepository.findByName("Name1").orElse(null).getId());
        genresIds.add(genreRepository.findByName("Name2").orElse(null).getId());
        assertFalse(filmRepository.
                findAllByGenres_IdIn(genresIds,PageRequest.of(0,2))
                .getContent()
                .isEmpty()
        );
    }

    @Test
    public void whenFindAllWithFilter_thenReturnFilms(){
        List<UUID> genresIds = new ArrayList<>();
        genresIds.add(genreRepository.findByName("Name1").orElse(null).getId());
        genresIds.add(genreRepository.findByName("Name2").orElse(null).getId());
        PageRequest.of(0,2);
        Set<String> genresNamesSet = new HashSet<>();
        genresNamesSet.add("Name1");
        FilmFilterRequest filter = new FilmFilterRequest(new PaginationRequest(5,0),null,genresNamesSet,null,null);
        System.out.println(filmRepository.findAll(FilmSpecification.withFilter(filter),
                PageRequest.of(0,2)));
        assertFalse(filmRepository.
                findAllByGenres_IdIn(genresIds,PageRequest.of(0,2))
                .getContent()
                .isEmpty()
        );
    }

}
