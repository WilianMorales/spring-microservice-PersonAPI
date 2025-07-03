package com.moradev.spring_microservice.application.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employment {
    private Long id;
    private String position;
    private String company;
    private Double salary;
    private Long personId;
}
