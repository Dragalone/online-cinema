package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.PageRequest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
@SpringBootTest
public class DirectorRepositoryTest {

    @Autowired
    private DirectorRepository directorRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @Test
    public void givenNewDirector_whenSave_thenSuccess(){
        Director director = new Director();
        director.setName("Name1234");
        director.setBirthdate(new GregorianCalendar(2024, Calendar.FEBRUARY, 11).getTime());
        director.setInformation("Lorem ipsum");
        Director insertedDirector = directorRepository.save(director);
        assertEquals(director.getName(), insertedDirector.getName());
        assertEquals(director.getBirthdate(), insertedDirector.getBirthdate());
    }
    @Test
    public void whenFindById_thenReturnDirector(){
        assertFalse(directorRepository.findById(UUID.fromString("f7f39016-e209-41f5-9e49-eef23eff3a68"))
                .isEmpty());
    }

    @Test
    public void whenFindByName_thenReturnDirector(){
        assertFalse(directorRepository.findByName("Name1")
                .isEmpty());
    }

    @Test
    public void whenFindAllByName_thenReturnDirector(){
        assertFalse(directorRepository.findAllByName("Name1", PageRequest.of(0,2))
                .getContent()
                .isEmpty());
    }

}
