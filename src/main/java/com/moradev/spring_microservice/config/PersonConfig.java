package com.moradev.spring_microservice.config;

import com.moradev.spring_microservice.domain.ports.EmploymentPersistencePort;
import com.moradev.spring_microservice.domain.ports.EmploymentServicePort;
import com.moradev.spring_microservice.domain.ports.PersonPersistencePort;
import com.moradev.spring_microservice.domain.ports.PersonServicePort;
import com.moradev.spring_microservice.domain.service.EmploymentServiceImpl;
import com.moradev.spring_microservice.domain.service.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {
    @Bean
    public PersonServicePort personService(PersonPersistencePort personPersistencePort) {
        return new PersonServiceImpl(personPersistencePort);
    }

    @Bean
    public EmploymentServicePort employmentService(EmploymentPersistencePort port) {
        return new EmploymentServiceImpl(port);
    }
}
