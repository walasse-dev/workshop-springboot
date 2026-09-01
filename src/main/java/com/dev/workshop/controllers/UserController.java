package com.dev.workshop.controllers;

import com.dev.workshop.entities.User;
import com.dev.workshop.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * REST controller for managing User operations.
 * Provides endpoints for retrieving, creating, updating, and deleting users.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService service;

    /**
     * Constructs UserController with the required UserService.
     * 
     * @param service the user service bean
     */
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Retrieves all registered users.
     * 
     * @return a ResponseEntity containing the list of users and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    /**
     * Retrieves a single user by their unique identifier.
     * 
     * @param id the user ID
     * @return a ResponseEntity containing the user and HTTP 200 OK status
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    /**
     * Inserts a new user into the database.
     * 
     * @param user the user object containing registration details
     * @return a ResponseEntity containing the created user, the resource URI, and HTTP 201 Created status
     */
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user) {
        User obj = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    /**
     * Deletes a user by their unique identifier.
     * 
     * @param id the user ID to delete
     * @return a ResponseEntity with HTTP 204 No Content status
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing user's information.
     * 
     * @param id the user ID to update
     * @param user the updated user details
     * @return a ResponseEntity containing the updated user and HTTP 200 OK status
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        user = service.update(id, user);
        return ResponseEntity.ok().body(user);
    }
}
