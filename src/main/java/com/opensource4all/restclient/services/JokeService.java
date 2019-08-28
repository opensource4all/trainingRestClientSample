package com.opensource4all.restclient.services;

import com.opensource4all.restclient.json.JokeResponse;
import com.opensource4all.restclient.properties.RestClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JokeService {
    private RestTemplate template;

    @Autowired
    RestClientProperties restClientProperties;

    // if ,and only if, you have a single constructor, spring will autowire RestTemplateBuilder
    public JokeService(RestTemplateBuilder builder) {
        template = builder.setConnectTimeout(5).setReadTimeout(5).build();
    }

    public String getJokeSync(String first, String last) {
        String url = restClientProperties.getJokeServiceBase() + "&firstName=" + first + "&lastName=" + last;
        return template.getForObject(url, JokeResponse.class)
                .getValue().getJoke();
    }

    public Mono<String> getJokeAsync(String first, String last) {
        String path = restClientProperties.getJokeServiceBase() + "&firstName={first}&lastName={last}";
        WebClient client = WebClient.create(restClientProperties.getClientBase());
        return client.get()
                .uri(path, first, last)
                .cookie("X-SESSION","cool")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(JokeResponse.class)
                .map(jokeResponse -> jokeResponse.getValue().getJoke());
    }

}
