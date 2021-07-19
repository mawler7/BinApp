package com.management.warehouse.service;

import com.management.warehouse.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Page<Order> getOrdersPaginated(Pageable pageable);
    List<Order> setDelivered(Integer id);

    void save(Order order);

    Order findById(int id);
    void deleteOrder(int id);
    void editOrder(Order order);


}
