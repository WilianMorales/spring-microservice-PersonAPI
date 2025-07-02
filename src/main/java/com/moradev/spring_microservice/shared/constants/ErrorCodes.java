package com.moradev.spring_microservice.shared.constants;

public class ErrorCodes {
    public static final int PERSON_NOT_FOUND = 1002;
    public static final int DUPLICATE_PERSON = 3001;
    public static final int BAD_REQUEST = 4000;
    public static final int INTERNAL_ERROR = 5001;

    private ErrorCodes() {
        // Previene la instanciación
    }
}
