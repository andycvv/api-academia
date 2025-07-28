package com.academia.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Getter
public class CustomValidationException extends RuntimeException {
    private final Map<String, Object> details;

    public CustomValidationException(Map<String, Object> details) {
        super("Validation failed");
        this.details = details;
    }
}
