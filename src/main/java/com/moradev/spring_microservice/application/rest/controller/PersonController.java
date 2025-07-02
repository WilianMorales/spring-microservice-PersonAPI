package com.moradev.spring_microservice.application.rest.controller;

import com.moradev.spring_microservice.application.rest.mapper.PersonRestMapper;
import com.moradev.spring_microservice.application.rest.model.request.PersonRequest;
import com.moradev.spring_microservice.application.rest.model.response.ErrorResponse;
import com.moradev.spring_microservice.application.rest.model.response.PersonResponse;
import com.moradev.spring_microservice.domain.exception.BussinessException;
import com.moradev.spring_microservice.domain.model.PersonModel;
import com.moradev.spring_microservice.domain.ports.PersonServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Persona", description = "Servicios asociados con la entidad persona")
@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonServicePort personService;
    private final PersonRestMapper personRestMapper;

    @Operation(
            summary = "Crear persona",
            description = "Este servicio se encarga de registrar una nueva persona"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Persona creada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PersonResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<PersonResponse> addPerson(@Valid @RequestBody PersonRequest personRequest) {
        PersonModel personModel = personRestMapper.mapToPersonModel(personRequest);
        PersonResponse personResponse = personRestMapper.mapToPersonResponse(
                this.personService.addPerson(personModel)
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personResponse);
    }

    @Operation(
            summary = "Actualizar persona",
            description = "Actualiza los datos de una persona existente por ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Persona actualizada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PersonResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Persona no encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePerson(
            @Parameter(description = "ID de la persona a actualizar", required = true)
            @PathVariable Long id,
            @Valid @RequestBody PersonRequest personRequest
    ) {
        PersonModel personModel = personRestMapper.mapToPersonModel(personRequest);
        personModel.setId(id);
        PersonResponse personResponse = personRestMapper.mapToPersonResponse(
                this.personService.updatePerson(personModel)
        );
        return ResponseEntity.ok(personResponse);
    }

    @Operation(
            summary = "Eliminar persona",
            description = "Elimina una persona existente por su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Persona eliminada exitosamente"),
            @ApiResponse(responseCode = "404",description = "Persona no encontrada"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(
            @Parameter(description = "ID de la persona a eliminar", required = true)
            @PathVariable Long id
    ) throws BussinessException {
        this.personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Listar personas",
            description = "Obtiene la lista completa de personas registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de personas obtenida correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                    schema = @Schema(implementation = PersonResponse.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAllPersons() {
        return ResponseEntity.ok(
                personRestMapper.mapToPersonListResponse(
                        this.personService.getPersons()
                )
        );
    }

    @Operation(
            summary = "Obtener persona por ID",
            description = "Retorna los datos de una persona específica según su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Persona encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PersonResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonById(
            @Parameter(description = "ID de la persona a consultar", required = true)
            @PathVariable Long id
    ) throws BussinessException {
        PersonModel person = personService.getById(id);
        return ResponseEntity.ok(personRestMapper.mapToPersonResponse(person));
    }

    /* @GetMapping("/test-error")
    public ResponseEntity<String> generarError() {
        throw new RuntimeException("Error de prueba");
    }*/

}
