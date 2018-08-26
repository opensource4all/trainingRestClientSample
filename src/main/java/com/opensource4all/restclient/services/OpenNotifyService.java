package com.opensource4all.restclient.services;

import com.opensource4all.restclient.json.openNotify.OpenNotifyAstrosResponse;
import com.opensource4all.restclient.json.openNotify.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenNotifyService {
    private final RestTemplate template;

    private String url = "http://api.open-notify.org/astros.json";
    @Autowired
    public OpenNotifyService(RestTemplateBuilder builder) {
        template = builder.build();
    }

    public People[] getAstros() {
        return template.getForObject(url, OpenNotifyAstrosResponse.class).getPeople();
    }

    public ResponseEntity<OpenNotifyAstrosResponse> getOpenNotifyResponseEntity() {
        ResponseEntity<OpenNotifyAstrosResponse> forEntity = template.getForEntity(url, OpenNotifyAstrosResponse.class);
        return forEntity;
    }

}
