package com.dev.workshop.controllers;

import com.dev.workshop.entities.Category;
import com.dev.workshop.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing Product Categories.
 * Provides endpoints to query product categories.
 */
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    private final CategoryService service;

    /**
     * Constructs CategoryController with the required CategoryService.
     * 
     * @param service the category service bean
     */
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    /**
     * Retrieves all product categories.
     * 
     * @return a ResponseEntity containing the list of categories and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    /**
     * Retrieves a single category by its unique identifier.
     * 
     * @param id the category ID
     * @return a ResponseEntity containing the category and HTTP 200 OK status
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}
