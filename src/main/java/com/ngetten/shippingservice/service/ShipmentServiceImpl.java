package com.ngetten.shippingservice.service;

import com.ngetten.shippingservice.config.KafkaProducerShipmentStatusUpdated;
import com.ngetten.shippingservice.entity.Shipment;
import com.ngetten.shippingservice.model.ShipmenUpdatedEvent;
import com.ngetten.shippingservice.repository.ShipmentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ShipmentServiceImpl {

    private ShipmentRepository shipmentRepository;
    private KafkaProducerShipmentStatusUpdated kafkaProducerShipmentStatusUpdated;;

    public Shipment create(Shipment shipment) {
        shipment.setStatus("PENDING");
        shipment.setEstimatedDelivery(LocalDateTime.now().plusDays(5));
        Shipment createdShipment =  shipmentRepository.save(shipment);

        ShipmenUpdatedEvent shipmenUpdatedEvent = new ShipmenUpdatedEvent();
        shipmenUpdatedEvent.setStatus(shipment.getStatus());
        shipmenUpdatedEvent.setOrderId(shipment.getOrderId());

        kafkaProducerShipmentStatusUpdated.sendShipmentCreatedEvent(shipmenUpdatedEvent);

        return createdShipment;
    }

    public Shipment update(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public Shipment trackShipment(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Shipment not found"));
    }

    public List<Shipment> findAllShipmentByCustomerId(Long customerId) {
        return shipmentRepository.findAllShipmentByCustomerId(customerId);
    }

    public Shipment findByOrderId(Long orderId) {
        return shipmentRepository.findByOrderId(orderId);
    }

    public void deleteById(Long id) {
        shipmentRepository.deleteById(id);
    }

    public Shipment updateShipmentStatus(Long orderId, String status) {
        Shipment shipment = trackShipment(orderId);
        shipment.setStatus(status);
        Shipment updatedShipment = shipmentRepository.save(shipment);

        ShipmenUpdatedEvent shipmenUpdatedEvent = new ShipmenUpdatedEvent();
        shipmenUpdatedEvent.setStatus(updatedShipment.getStatus());
        shipmenUpdatedEvent.setOrderId(updatedShipment.getOrderId());

        kafkaProducerShipmentStatusUpdated.sendShipmentCreatedEvent(shipmenUpdatedEvent);

        return updatedShipment;
    }

}
