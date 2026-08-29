package com.dev.workshop.services;

import com.dev.workshop.entities.Product;
import com.dev.workshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository userRepository;

    public ProductService(ProductRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Product> findAll() {
        return userRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> obj = userRepository.findById(id);
        return obj.get();
    }
}
