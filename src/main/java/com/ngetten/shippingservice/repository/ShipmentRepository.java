package com.ngetten.shippingservice.repository;

import com.ngetten.shippingservice.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Shipment findByOrderId(Long orderId);
    Shipment findByUserId(Long userId);

    @Query("select s from Shipment s where s.userId =: customerId")
    List<Shipment> findAllShipmentByCustomerId(Long customerId);
}
