package com.management.warehouse.repository;

import com.management.warehouse.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    void deleteById(int id);
    Order findById(int id);
    void save(int id);
}
