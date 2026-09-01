package com.dev.workshop.services.exceptions;

import java.io.Serial;

/**
 * Custom runtime exception thrown when a database integrity violation or error occurs.
 */
public class DatabaseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs DatabaseException with error message.
     * 
     * @param msg the detail message
     */
    public DatabaseException(String msg) {
        super(msg);
    }
}