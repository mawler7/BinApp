package com.management.warehouse.model.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {


    Optional<Order> findById(UUID id);


    void save(Optional<Order> order);

    List<Order> findAllByType(String type);
}
