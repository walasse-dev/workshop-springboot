package com.dev.workshop.entities.pk;

import com.dev.workshop.entities.Order;
import com.dev.workshop.entities.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Embedded composite primary key for OrderItem entity.
 */
@Embeddable
public class OrderItemPK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Gets the Order part of the primary key.
     * 
     * @return the Order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the Order part of the primary key.
     * 
     * @param order the Order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets the Product part of the primary key.
     * 
     * @return the Product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the Product part of the primary key.
     * 
     * @param product the Product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
