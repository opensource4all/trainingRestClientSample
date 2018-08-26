package com.opensource4all.restclient.services;

import com.opensource4all.restclient.json.JokeResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {
    private RestTemplate template;
    // if ,and only if, you have a single constructor, spring will autowire RestTemplateBuilder
    public JokeService(RestTemplateBuilder builder) {
        template = builder.build();
    }

    public String getJokeSync(String first, String last) {
        String base = "http://api.icndb.com/jokes/random?limitTo=[nerdy]";
        String url = base + "&firstName=" + first + "&lastName=" + last;
        return template.getForObject(url, JokeResponse.class)
                .getValue().getJoke();
    }

}
