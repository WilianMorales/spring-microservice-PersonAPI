package com.moradev.spring_microservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    private final OpenApiProperties properties;

    public OpenAPIConfiguration(OpenApiProperties properties) {
        this.properties = properties;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .contact(new Contact()
                        .name(properties.getContact().getName())
                        .email(properties.getContact().getEmail())
                );

        List<Server> serverList = properties.getServers().values().stream()
                .map(s -> new Server().url(s.getUrl()).description(s.getDescription()))
                .toList();

        return new OpenAPI().info(info).servers(serverList);
    }
}
