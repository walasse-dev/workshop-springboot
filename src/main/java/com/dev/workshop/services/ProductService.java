package com.dev.workshop.services;

import com.dev.workshop.entities.Product;
import com.dev.workshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to Products.
 */
@Service
public class ProductService {
    private final ProductRepository userRepository;

    /**
     * Constructs ProductService with the given ProductRepository.
     * 
     * @param userRepository the product repository bean
     */
    public ProductService(ProductRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all products from the database.
     * 
     * @return a list of all products
     */
    public List<Product> findAll() {
        return userRepository.findAll();
    }

    /**
     * Finds a product by its unique identifier.
     * 
     * @param id the product ID
     * @return the found Product entity
     */
    public Product findById(Long id) {
        Optional<Product> obj = userRepository.findById(id);
        return obj.get();
    }
}
