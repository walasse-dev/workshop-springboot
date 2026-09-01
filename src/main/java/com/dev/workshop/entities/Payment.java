package com.dev.workshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Entity representing an Order Payment transaction.
 */
@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment;

    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order;

    /**
     * Empty constructor.
     */
    public Payment() {
    }

    /**
     * Constructs a Payment with all attributes.
     * 
     * @param id payment unique identifier
     * @param moment instant when payment was made
     * @param order the associated Order
     */
    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    /**
     * Gets payment ID.
     * 
     * @return payment ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets payment ID.
     * 
     * @param id payment ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets payment moment timestamp.
     * 
     * @return payment moment instant
     */
    public Instant getMoment() {
        return moment;
    }

    /**
     * Sets payment moment timestamp.
     * 
     * @param moment payment moment to set
     */
    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
