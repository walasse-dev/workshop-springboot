package com.dev.workshop.controllers;

import com.dev.workshop.entities.Order;
import com.dev.workshop.entities.User;
import com.dev.workshop.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing Orders.
 * Provides endpoints to query order transactions.
 */
@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderService service;

    /**
     * Constructs OrderController with the required OrderService.
     * 
     * @param service the order service bean
     */
    public OrderController(OrderService service) {
        this.service = service;
    }

    /**
     * Retrieves all orders placed in the system.
     * 
     * @return a ResponseEntity containing the list of orders and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    /**
     * Retrieves a single order by its unique identifier.
     * 
     * @param id the order ID
     * @return a ResponseEntity containing the order and HTTP 200 OK status
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}
