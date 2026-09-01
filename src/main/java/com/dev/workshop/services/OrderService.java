package com.dev.workshop.services;

import com.dev.workshop.entities.Order;
import com.dev.workshop.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to Orders.
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    /**
     * Constructs OrderService with the given OrderRepository.
     * 
     * @param orderRepository the order repository bean
     */
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Retrieves all orders from the database.
     * 
     * @return a list of all orders
     */
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * Finds an order by its unique identifier.
     * 
     * @param id the order ID
     * @return the found Order entity
     */
    public Order findById(Long id) {
        Optional<Order> obj = orderRepository.findById(id);
        return obj.get();
    }
}
