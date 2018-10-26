package com.opensource4all.restclient.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "restclient")
@Data
public class RestClientProperties {
    String astrosUrl;
    String jokeServiceBase;
    String clientBase;

}
