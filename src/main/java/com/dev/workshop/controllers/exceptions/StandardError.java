package com.dev.workshop.controllers.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * Standard error DTO used for structuring error responses across the REST API.
 */
public class StandardError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    /**
     * Empty constructor.
     */
    public StandardError() {
    }

    /**
     * Constructs a StandardError with all fields.
     * 
     * @param timestamp the time when the error occurred
     * @param status HTTP status code
     * @param error error type description
     * @param message detailed error message
     * @param path request URI path where the error occurred
     */
    public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * Gets the error timestamp.
     * 
     * @return the instant timestamp
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the error timestamp.
     * 
     * @param timestamp the instant timestamp to set
     */
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the HTTP status code.
     * 
     * @return the status integer
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code.
     * 
     * @param status the status integer to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets the error description.
     * 
     * @return the error string
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error description.
     * 
     * @param error the error string to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Gets the error message.
     * 
     * @return the message string
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     * 
     * @param message the message string to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the request path.
     * 
     * @return the path string
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the request path.
     * 
     * @param path the path string to set
     */
    public void setPath(String path) {
        this.path = path;
    }
}
