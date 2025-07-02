package com.moradev.spring_microservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmploymentModel {

    private Long id;
    private String position;
    private String company;
    private Double salary;
    private Long personId;
}
