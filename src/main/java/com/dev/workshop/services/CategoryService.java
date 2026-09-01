package com.dev.workshop.services;

import com.dev.workshop.entities.Category;
import com.dev.workshop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to Categories.
 */
@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    /**
     * Constructs CategoryService with the given CategoryRepository.
     * 
     * @param categoryRepository the category repository bean
     */
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retrieves all categories from the database.
     * 
     * @return a list of all categories
     */
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    /**
     * Finds a category by its unique identifier.
     * 
     * @param id the category ID
     * @return the found Category entity
     */
    public Category findById(Long id){
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.get();
    }
}
