package com.dev.workshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * Entity representing a Product Category.
 */
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    /**
     * Empty constructor.
     */
    public Category() {
    }

    /**
     * Constructs a Category with ID and name.
     * 
     * @param id category unique identifier
     * @param name category name
     */
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets category ID.
     * 
     * @return category ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets category ID.
     * 
     * @param id category ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets category name.
     * 
     * @return category name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets category name.
     * 
     * @param name category name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets products associated with this category.
     * 
     * @return set of products
     */
    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
