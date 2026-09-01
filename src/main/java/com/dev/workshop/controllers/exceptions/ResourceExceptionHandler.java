package com.dev.workshop.controllers.exceptions;

import com.dev.workshop.services.exceptions.DatabaseException;
import com.dev.workshop.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serial;
import java.time.Instant;

/**
 * Global exception handler for REST controllers.
 * Intercepts custom exceptions and builds standardized error responses.
 */
@ControllerAdvice
public class ResourceExceptionHandler {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Handles ResourceNotFoundException and returns HTTP 404 Not Found.
     * 
     * @param e the resource not found exception
     * @param request the HTTP request that triggered the exception
     * @return a ResponseEntity containing the StandardError and HTTP 404 status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Handles DatabaseException and returns HTTP 400 Bad Request.
     * 
     * @param e the database exception
     * @param request the HTTP request that triggered the exception
     * @return a ResponseEntity containing the StandardError and HTTP 400 status
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
