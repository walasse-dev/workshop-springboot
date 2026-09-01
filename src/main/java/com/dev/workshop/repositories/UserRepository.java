package com.dev.workshop.repositories;

import com.dev.workshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for User entity database operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
