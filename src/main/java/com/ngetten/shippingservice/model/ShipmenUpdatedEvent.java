package com.ngetten.shippingservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ShipmenUpdatedEvent {
    private Long orderId;
    private Long paymentId;
    private String status;
}
