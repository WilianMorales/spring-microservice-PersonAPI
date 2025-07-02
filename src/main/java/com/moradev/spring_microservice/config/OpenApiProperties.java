package com.moradev.spring_microservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Data
@Component
@ConfigurationProperties(prefix = "open-api")
public class OpenApiProperties {

    private String title;
    private String description;
    private Contact contact;
    private Map<String, Server> servers;

    @Data
    public static class Contact {
        private String name;
        private String email;
    }

    @Data
    public static class Server {
        private String url;
        private String description;
    }
}
