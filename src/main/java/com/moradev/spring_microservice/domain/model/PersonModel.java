package com.moradev.spring_microservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PersonModel {

    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String address;

}
