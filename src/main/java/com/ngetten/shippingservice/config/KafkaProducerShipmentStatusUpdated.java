package com.ngetten.shippingservice.config;

import com.ngetten.shippingservice.model.ShipmenUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@AllArgsConstructor
public class KafkaProducerShipmentStatusUpdated {

    private final KafkaTemplate<String, ShipmenUpdatedEvent> kafkaTemplate;
    private static final String TOPIC = "shipment-created";

    // Publish an event to Kafka to notify other services (e.g., notification)
    public void sendShipmentCreatedEvent(ShipmenUpdatedEvent shipmenUpdatedEvent) {
        kafkaTemplate.send(TOPIC, shipmenUpdatedEvent);
    }
}
