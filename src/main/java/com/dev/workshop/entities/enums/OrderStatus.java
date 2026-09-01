package com.dev.workshop.entities.enums;

/**
 * Enumeration representing the possible states of an Order.
 */
public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    OrderStatus(int code) {
        this.code = code;
    }

    /**
     * Gets the integer code representing the order status.
     * 
     * @return status code integer
     */
    public int getCode() {
        return code;
    }

    /**
     * Converts an integer code to its corresponding OrderStatus enum value.
     * 
     * @param code the status code integer
     * @return the corresponding OrderStatus
     * @throws IllegalArgumentException if the code is invalid
     */
    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code!");
    }
}
