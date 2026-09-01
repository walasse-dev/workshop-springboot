package com.dev.workshop.repositories;

import com.dev.workshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Category entity database operations.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
