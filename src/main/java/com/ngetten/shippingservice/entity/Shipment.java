package com.ngetten.shippingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

/**
 * Key Features:
 * Create Shipment: Handles the shipment creation after the order is placed.
 * Track Shipment: Allows tracking of the shipping status.
 */

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Long userId;
    private String address;
    private String status; // e.g., PENDING, SHIPPED, DELIVERED
    private LocalDateTime estimatedDelivery;

}
