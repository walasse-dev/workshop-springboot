package com.dev.workshop.controllers;

import com.dev.workshop.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping
    public ResponseEntity<User> findAll(){
        User user = new User(1L, "Maria", "maria@gmail.com", "9999999", "12345");
        return ResponseEntity.ok().body(user);
    }
}
