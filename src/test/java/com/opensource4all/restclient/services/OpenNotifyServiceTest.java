package com.opensource4all.restclient.services;

import com.opensource4all.restclient.json.openNotify.OpenNotifyAstrosResponse;
import com.opensource4all.restclient.json.openNotify.People;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenNotifyServiceTest {
    @Autowired
    OpenNotifyService openNotifyService;
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Test
    public void getAstros() {
        People[] astros = openNotifyService.getAstros();
        
        if (astros != null) {
            logger.info(" astros{people in space]: " + Arrays.toString(astros));
        }
    }

    @Test
    public void getOpenNotifyResponseEntity() {
        ResponseEntity<OpenNotifyAstrosResponse> openNotifyResponseEntity = openNotifyService.getOpenNotifyResponseEntity();
        assertEquals(openNotifyResponseEntity.getStatusCode(), HttpStatus.OK);
        logger.info(openNotifyResponseEntity.toString());

    }
}