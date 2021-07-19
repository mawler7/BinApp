package com.management.warehouse.repository;

import com.management.warehouse.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    void deleteById(int id);

    Order findById(int id);

}