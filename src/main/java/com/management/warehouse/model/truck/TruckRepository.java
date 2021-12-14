package com.management.warehouse.model.truck;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TruckRepository extends JpaRepository<Truck, UUID> {

    Optional<Truck> findByRegNumberAllIgnoreCase(String regNumber);

}