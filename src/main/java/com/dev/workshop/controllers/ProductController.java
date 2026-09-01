package com.dev.workshop.controllers;

import com.dev.workshop.entities.Product;
import com.dev.workshop.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing Products.
 * Provides endpoints to query product catalog.
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService service;

    /**
     * Constructs ProductController with the required ProductService.
     * 
     * @param service the product service bean
     */
    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Retrieves all products in the catalog.
     * 
     * @return a ResponseEntity containing the list of products and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    /**
     * Retrieves a single product by its unique identifier.
     * 
     * @param id the product ID
     * @return a ResponseEntity containing the product and HTTP 200 OK status
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}
