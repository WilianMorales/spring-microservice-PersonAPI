package com.moradev.spring_microservice.application.rest.model.response;

import com.moradev.spring_microservice.application.rest.model.Employment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO de respuesta del empleo")
public class EmploymentResponse extends Employment {
}
