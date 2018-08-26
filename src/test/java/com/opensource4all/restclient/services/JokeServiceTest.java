package com.opensource4all.restclient.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeServiceTest {

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private JokeService service;

    @Test
    public void getJokeSynch() {
        String joke = service.getJokeSync("foo","bar");
        logger.info(joke);
        assertTrue(joke.contains("foo") || joke.contains("bar"));

    }

}