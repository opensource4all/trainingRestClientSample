package com.opensource4all.restclient.services;

//import org.junit.Test;

import com.opensource4all.restclient.entities.Joke;
import com.opensource4all.restclient.repository.JokeJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.Duration;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class JokeServiceTest {

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private JokeService service;
    @Autowired @Qualifier("jokeJpa")
    JokeJpa jokeJpa;
    @Mock
    private JokeService jokeServiceMock;


    @Test
    public void getJokeSynch() {
        String joke = service.getJokeSync("foo","bar");
        logger.info(joke);
        assertTrue(joke.contains("foo") || joke.contains("bar"));
    }

    @Test
    public void getJokeAsync() {
        String joke = service.getJokeAsync("foo", "bar")
                .block(Duration.ofSeconds(2));
        logger.info(" In getJokeAsync with a joke: " + joke);
        assertTrue(joke.contains("foo") || joke.contains("bar"));
    }

    @Test
    public void testJokeJpa() {
        String joke = service.getJokeAsync("foo", "bar")
                .block(Duration.ofSeconds(2));
        logger.info(" In getJokeAsync with a joke: " + joke);
        Joke jokeAfter = jokeJpa.save(new Joke(joke));
        assertNotNull(jokeAfter.getId());
    }

    @Test
    public void testJokeServiceMockito() {
        jokeServiceMock.getJokeAsync("first", "last");
        String stringExpected = "George Smith likes to code in Kotlin now because it's cool.";
        when(jokeServiceMock.getJokeSync("George", "Smith")).thenReturn(stringExpected);
        logger.info(jokeServiceMock.getJokeSync("George","Smith"));
        assertEquals(stringExpected, jokeServiceMock.getJokeSync("George", "Smith"));
    }

}