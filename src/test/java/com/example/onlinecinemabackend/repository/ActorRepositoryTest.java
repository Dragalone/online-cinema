package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.PageRequest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
@SpringBootTest
public class ActorRepositoryTest {

    @Autowired
    private ActorRepository actorRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @Test
    public void givenNewActor_whenSave_thenSuccess(){
        Actor actor = new Actor();
        actor.setName("TestActor");
        actor.setBirthdate(new GregorianCalendar(2023, Calendar.FEBRUARY, 11).toInstant());
        Actor insertedActor = actorRepository.save(actor);
        assertEquals(actor.getName(), insertedActor.getName());
        assertEquals(actor.getBirthdate(), insertedActor.getBirthdate());
    }

    @Test
    public void whenFindById_thenReturnActor(){
        assertFalse(actorRepository.findById(UUID.fromString("c77ad2be-0281-4458-9d66-892393ea4999"))
                .isEmpty());
    }

    @Test
    public void whenFindByName_thenReturnActor(){
        assertFalse(actorRepository.findByName("Name1")
                .isEmpty());
    }

    @Test
    public void whenFindAllByName_thenReturnActor(){
        assertFalse(actorRepository.findAllByName("Name1", PageRequest.of(0,2))
                .getContent()
                .isEmpty());
    }
}
