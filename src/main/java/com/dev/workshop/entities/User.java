package com.dev.workshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity representing a system User/Client.
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public User() {
    }

    /**
     * Constructs a User with all attributes.
     * 
     * @param id user unique identifier
     * @param name user full name
     * @param email user email address
     * @param phone user phone number
     * @param password user password
     */
    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    /**
     * Gets the user ID.
     * 
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user ID.
     * 
     * @param id the user ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user name.
     * 
     * @return the name string
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user name.
     * 
     * @param name the name string to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user email.
     * 
     * @return the email string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user email.
     * 
     * @param email the email string to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user phone.
     * 
     * @return the phone string
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the user phone.
     * 
     * @param phone the phone string to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the user password.
     * 
     * @return the password string
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user password.
     * 
     * @param password the password string to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the list of orders placed by the user.
     * 
     * @return list of orders
     */
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
