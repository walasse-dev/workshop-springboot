package com.dev.workshop.repositories;

import com.dev.workshop.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for OrderItem entity database operations.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
