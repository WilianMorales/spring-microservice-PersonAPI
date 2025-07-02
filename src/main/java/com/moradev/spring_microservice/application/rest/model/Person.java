package com.moradev.spring_microservice.application.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Entidad persona que representa información básica de contacto")
@NotBlank(message = "El nombre es obligatorio")
@Getter
@Setter
public class Person {

    @Schema(description = "Identificador único de la persona", example = "1")
    private Long id;

    @Schema(description = "Nombre de la persona", example = "Wilian")
    private String name;

    @Schema(description = "Apellido de la persona", example = "Morales")
    private String lastName;

    @Schema(description = "Número de teléfono de la persona", example = "51-987654321")
    private String phone;

    @Schema(description = "Dirección de la persona", example = "Av. Siempre Viva 742")
    private String address;
}
