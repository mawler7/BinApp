package com.management.warehouse.service;

import com.management.warehouse.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getAllOrders();

    Page<Order> getOrdersPaginated(Pageable pageable);

    List<Order> setDelivered(Integer id);

    void saveOrder(Order order);

    void updateOrder(Order order);

    Order findById(int id);

    void save(int id);

    void deleteOrder(int id);

    List<Order> showUpdatedForm();

    Optional<Order> getOrderById(Integer id);
}
