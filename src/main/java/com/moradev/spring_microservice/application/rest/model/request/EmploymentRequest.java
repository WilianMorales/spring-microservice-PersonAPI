package com.moradev.spring_microservice.application.rest.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO para registrar un empleo")
public class EmploymentRequest {

    @Schema(description = "Identificador único del empleo", example = "10")
    private Long id;

    @Schema(description = "Nombre del puesto del empleo", example = "Desarrollador Backend")
    @NotBlank(message = "El puesto es obligatorio")
    private String position;

    @Schema(description = "Nombre de la empresa", example = "OpenAI Inc.")
    @NotBlank(message = "La empresa es obligatoria")
    private String company;

    @Schema(description = "Salario del empleo", example = "8500.00")
    @NotNull(message = "El salario es obligatorio")
    @Positive(message = "El salario debe ser positivo")
    private Double salary;

    @Schema(example = "1", description = "ID de la persona asociada")
    @NotNull(message = "El ID de la persona es obligatorio")
    private Long personId;
}
