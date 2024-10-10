package com.ngetten.shippingservice.controller;

import com.ngetten.shippingservice.entity.Shipment;
import com.ngetten.shippingservice.service.ShipmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Shipping Service
 * Manages shipping rates, shipping provider integrations, and delivery status tracking.
 * Technologies: Node.js with integration to external shipping APIs (e.g., DHL, FedEx).
 *
 */

@RestController
@RequestMapping("/shipment")
public class ShippingController {

    @Autowired
    private ShipmentServiceImpl shipmentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Shipment shipment) {
        Shipment createdShipment = shipmentService.create(shipment);
        return ResponseEntity.ok(createdShipment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Shipment shipment = shipmentService.trackShipment(id);
        return ResponseEntity.ok(shipment);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getByOrderId(@PathVariable Long orderId) {
        Shipment shipment = shipmentService.findByOrderId(orderId);
        return ResponseEntity.ok(shipment);
    }

    @GetMapping("/customer/customerId")
    public ResponseEntity<?> getByCustomerId(@RequestParam Long customerId) {
        List<Shipment> shipments = shipmentService.findAllShipmentByCustomerId(customerId);
        return ResponseEntity.ok(shipments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Shipment shipment) {
        Shipment updatedShipment = shipmentService.update(shipment);
        return ResponseEntity.ok(updatedShipment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteById(id);
        return ResponseEntity.ok("Deleted shipment");
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateShipmentStatus(@PathVariable Long id, @RequestParam String status) {
        Shipment shipment = shipmentService.updateShipmentStatus(id, status);
        return ResponseEntity.ok(shipment);
    }

}
