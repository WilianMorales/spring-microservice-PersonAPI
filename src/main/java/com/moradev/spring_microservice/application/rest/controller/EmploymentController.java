package com.moradev.spring_microservice.application.rest.controller;

import com.moradev.spring_microservice.application.rest.mapper.EmploymentRestMapper;
import com.moradev.spring_microservice.application.rest.model.request.EmploymentRequest;
import com.moradev.spring_microservice.application.rest.model.response.EmploymentResponse;
import com.moradev.spring_microservice.application.rest.model.response.ErrorResponse;
import com.moradev.spring_microservice.domain.exception.BussinessException;
import com.moradev.spring_microservice.domain.model.EmploymentModel;
import com.moradev.spring_microservice.domain.ports.EmploymentServicePort;
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

@Tag(name = "Empleos", description = "Servicios relacionados al empleo de una persona")
@RestController
@RequestMapping("/api/employments")
@RequiredArgsConstructor
public class EmploymentController {

    private final EmploymentServicePort employmentService;
    private final EmploymentRestMapper employmentRestMapper;

    @Operation(
            summary = "Registrar un empleo",
            description = "Asocia un nuevo empleo a una persona"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Empleo creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmploymentResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<EmploymentResponse> addEmployment(@Valid @RequestBody EmploymentRequest employmentRequest) {
        EmploymentModel model = employmentRestMapper.mapToEmploymentModel(employmentRequest);
        EmploymentResponse employmentResponse = employmentRestMapper.mapToEmploymentResponse(
                employmentService.addEmployment(model)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(employmentResponse);
    }

    @Operation(
            summary = "Obtener empleos por persona",
            description = "Obtiene todos los empleos de una persona"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de empleos obtenida",
                    content = @Content(
                            mediaType = "application/json",
                            array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                    schema = @Schema(implementation = EmploymentResponse.class)
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/person/{personId}")
    public ResponseEntity<List<EmploymentResponse>> getEmploymentsByPersonId(
            @Parameter(description = "ID de la persona", required = true)
            @PathVariable Long personId
    ) {
        List<EmploymentModel> modelList = employmentService.getEmploymentsByPersonId(personId);
        return ResponseEntity.ok(employmentRestMapper.mapToEmploymentListResponse(modelList));
    }

    @Operation(
            summary = "Eliminar un empleo",
            description = "Elimina un empleo por su ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Empleo eliminado exitosamente"),
            @ApiResponse(responseCode = "404",description = "Empleo no encontrada"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployment(
            @Parameter(description = "ID del empleo a eliminar", required = true)
            @PathVariable Long id
    ) throws BussinessException {
        this.employmentService.deleteEmployment(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualizar un empleo",
            description = "Actualiza los datos de un empleo existente por su ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Empleo actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmploymentResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Empleo no encontrado"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmploymentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody EmploymentRequest request
    ) {
        EmploymentModel existing = employmentService.findById(id);
        employmentRestMapper.updateModelFromRequest(request, existing);
        EmploymentModel updated = employmentService.updateEmployment(existing);
        return ResponseEntity.ok(employmentRestMapper.mapToEmploymentResponse(updated));
    }
}
