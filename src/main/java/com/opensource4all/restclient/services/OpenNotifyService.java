package com.opensource4all.restclient.services;

import com.opensource4all.restclient.json.openNotify.OpenNotifyAstrosResponse;
import com.opensource4all.restclient.json.openNotify.People;
import com.opensource4all.restclient.properties.RestClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenNotifyService {
    private final RestTemplate template;

    @Autowired
    RestClientProperties restClientProperties;
    @Autowired
    public OpenNotifyService(RestTemplateBuilder builder) {
        template = builder.build();
    }

    public People[] getAstros() {
        return template.getForObject(restClientProperties.getAstrosUrl(), OpenNotifyAstrosResponse.class).getPeople();
    }

    public ResponseEntity<OpenNotifyAstrosResponse> getOpenNotifyResponseEntity() {
        ResponseEntity<OpenNotifyAstrosResponse> forEntity = template.getForEntity(restClientProperties.getAstrosUrl(), OpenNotifyAstrosResponse.class);
        return forEntity;
    }

}
