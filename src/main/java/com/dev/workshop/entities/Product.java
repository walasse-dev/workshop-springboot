package com.dev.workshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a Product in the catalog.
 */
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    /**
     * Empty constructor.
     */
    public Product() {
    }

    /**
     * Constructs a Product with all attributes.
     * 
     * @param id product unique identifier
     * @param name product name
     * @param description product description
     * @param price product price
     * @param imgUrl product image URL
     */
    public Product(Long id, String name, String description, BigDecimal price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    /**
     * Gets product ID.
     * 
     * @return product ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets product ID.
     * 
     * @param id product ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets product name.
     * 
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets product name.
     * 
     * @param name product name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets product description.
     * 
     * @return product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets product description.
     * 
     * @param description product description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets product price.
     * 
     * @return product price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets product price.
     * 
     * @param price product price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets product image URL.
     * 
     * @return image URL
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Sets product image URL.
     * 
     * @param imgUrl image URL to set
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Gets categories associated with this product.
     * 
     * @return set of categories
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /**
     * Gets orders containing this product.
     * 
     * @return set of orders
     */
    @JsonIgnore
    public Set<Order> getOrders() {
        Set<Order> set = new HashSet<>();
        for (OrderItem x : items) {
            set.add(x.getOrder());
        }
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
