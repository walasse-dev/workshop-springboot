package com.dev.workshop.entities;

import com.dev.workshop.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a Customer Order.
 */
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    /**
     * Empty constructor.
     */
    public Order() {
    }

    /**
     * Constructs an Order with all attributes.
     * 
     * @param id order unique identifier
     * @param moment instant when the order was placed
     * @param orderStatus status of the order
     * @param client the user client who placed the order
     */
    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        this.client = client;
        setOrderStatus(orderStatus);
    }

    /**
     * Calculates the total price of all items in this order.
     * 
     * @return the total BigDecimal amount
     */
    public BigDecimal getTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.getSubTotal());
        }
        return total;
    }

    /**
     * Gets order ID.
     * 
     * @return order ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets order ID.
     * 
     * @param id order ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets order moment timestamp.
     * 
     * @return order instant moment
     */
    public Instant getMoment() {
        return moment;
    }

    /**
     * Sets order moment timestamp.
     * 
     * @param moment order instant to set
     */
    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    /**
     * Gets the client who placed the order.
     * 
     * @return the User client
     */
    public User getClient() {
        return client;
    }

    /**
     * Sets the client who placed the order.
     * 
     * @param client the User client to set
     */
    public void setClient(User client) {
        this.client = client;
    }

    /**
     * Gets the order status.
     * 
     * @return OrderStatus enum
     */
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    /**
     * Sets the order status.
     * 
     * @param orderStatus OrderStatus enum to set
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    /**
     * Gets the set of order items.
     * 
     * @return set of OrderItem
     */
    public Set<OrderItem> getItems() {
        return items;
    }

    /**
     * Gets the payment associated with the order.
     * 
     * @return Payment entity
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Sets the payment associated with the order.
     * 
     * @param payment Payment entity to set
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
