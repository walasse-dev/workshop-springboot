package com.dev.workshop.entities;

import com.dev.workshop.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entity representing an item within an Order.
 */
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    private Integer quantity;
    private BigDecimal price;

    /**
     * Empty constructor.
     */
    public OrderItem() {
    }

    /**
     * Constructs an OrderItem with order, product, quantity, and unit price.
     * 
     * @param order the order
     * @param product the product
     * @param quantity quantity ordered
     * @param price unit price at purchase time
     */
    public OrderItem(Order order, Product product, Integer quantity, BigDecimal price) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Gets the associated Order.
     * 
     * @return the Order
     */
    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    /**
     * Sets the associated Order.
     * 
     * @param order the Order to set
     */
    public void setOrder(Order order) {
        id.setOrder(order);
    }

    /**
     * Gets the associated Product.
     * 
     * @return the Product
     */
    public Product getProduct() {
        return id.getProduct();
    }

    /**
     * Sets the associated Product.
     * 
     * @param product the Product to set
     */
    public void setProduct(Product product) {
        id.setProduct(product);
    }

    /**
     * Gets the unit price.
     * 
     * @return unit price BigDecimal
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the unit price.
     * 
     * @param price unit price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the quantity.
     * 
     * @return quantity integer
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     * 
     * @param quantity quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates the subtotal for this order item (price * quantity).
     * 
     * @return subtotal BigDecimal
     */
    public BigDecimal getSubTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
