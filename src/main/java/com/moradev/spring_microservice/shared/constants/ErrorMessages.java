package com.moradev.spring_microservice.shared.constants;

public class ErrorMessages {
    public static final String PERSON_NOT_FOUND = "La persona con el ID proporcionado no existe.";
    public static final String DUPLICATE_PERSON = "Ya existe una persona registrada con estos datos.";
    public static final String BAD_REQUEST = "Solicitud inválida. Verifica los datos enviados.";
    public static final String INTERNAL_ERROR = "Ocurrió un error inesperado en el servidor.";

    private ErrorMessages() {
    }
}
