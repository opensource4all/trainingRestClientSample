package com.opensource4all.restclient.repository;

import com.opensource4all.restclient.entities.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class JokeJpa {
    @Autowired
    EntityManager entityManager;

    public Joke save(Joke joke) {
        entityManager.persist(joke);
        return joke;
    }

}
