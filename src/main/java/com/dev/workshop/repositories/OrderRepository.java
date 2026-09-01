package com.dev.workshop.repositories;

import com.dev.workshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Order entity database operations.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
