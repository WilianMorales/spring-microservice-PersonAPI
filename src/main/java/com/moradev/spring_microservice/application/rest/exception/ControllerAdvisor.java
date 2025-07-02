package com.moradev.spring_microservice.application.rest.exception;

import com.moradev.spring_microservice.application.rest.model.response.ErrorResponse;
import com.moradev.spring_microservice.domain.exception.PersonNotFoundException;
import com.moradev.spring_microservice.shared.constants.ErrorCodes;
import com.moradev.spring_microservice.shared.constants.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePersonNotFound(
            PersonNotFoundException ex,
            WebRequest webRequest
    ) {
        ErrorResponse error = ErrorResponse.builder()
                .code(ErrorCodes.PERSON_NOT_FOUND)
                .cause(ErrorMessages.PERSON_NOT_FOUND)
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(
            Exception ex,
            WebRequest request
    ) {
        ErrorResponse error = ErrorResponse.builder()
                .code(ErrorCodes.INTERNAL_ERROR)
                .cause(ErrorMessages.INTERNAL_ERROR)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
