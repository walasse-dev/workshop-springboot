package com.dev.workshop.services;

import com.dev.workshop.entities.User;
import com.dev.workshop.repositories.UserRepository;
import com.dev.workshop.services.exceptions.DatabaseException;
import com.dev.workshop.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to Users.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Constructs UserService with the given UserRepository.
     * 
     * @param userRepository the user repository bean
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users from the database.
     * 
     * @return a list of all users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Finds a user by their unique identifier.
     * 
     * @param id the user ID
     * @return the found User entity
     * @throws ResourceNotFoundException if the user is not found
     */
    public User findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * Inserts a new user into the database.
     * 
     * @param user the user to create
     * @return the created User entity
     */
    public User insert(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their unique identifier.
     * 
     * @param id the user ID to delete
     * @throws ResourceNotFoundException if the user does not exist
     * @throws DatabaseException if there is a data integrity violation
     */
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Updates an existing user's information.
     * 
     * @param id the user ID to update
     * @param user the new user details
     * @return the updated User entity
     * @throws ResourceNotFoundException if the user is not found
     */
    public User update(Long id, User user) {
        try {
            User obj = userRepository.getReferenceById(id);
            updateData(obj, user);
            return userRepository.save(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    /**
     * Helper method to update persistent user data from source object.
     * 
     * @param obj the persistent user entity
     * @param user the source user object with new data
     */
    private void updateData(User obj, User user) {
        obj.setName(user.getName());
        obj.setEmail(user.getEmail());
        obj.setPhone(user.getPhone());
    }
}
