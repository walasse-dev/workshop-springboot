package com.dev.workshop.repositories;

import com.dev.workshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Product entity database operations.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
