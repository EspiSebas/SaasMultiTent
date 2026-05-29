package com.example.SaaSMultiTentBackEnd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException .class)
    public ResponseEntity<?> handleValidationErrors(
            MethodArgumentNotValidException ex
    ){

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errors.put(
                            error.getField(),
                            error.getDefaultMessage()
                    );
                });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(
            ResourceNotFoundException ex
    ){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorized(
            UnauthorizedException ex
    ){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(
            BadRequestException ex
    ){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(
            Exception ex
    ){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "message", "Internal server error"
                ));
    }
}
