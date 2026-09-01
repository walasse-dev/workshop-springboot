package com.dev.workshop.services.exceptions;

import java.io.Serial;

/**
 * Custom runtime exception thrown when a requested resource is not found in the database.
 */
public class ResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs ResourceNotFoundException with identifier details.
     * 
     * @param id the missing resource identifier
     */
    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id: " + id);
    }
}
