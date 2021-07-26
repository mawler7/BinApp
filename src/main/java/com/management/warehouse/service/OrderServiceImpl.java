package com.management.warehouse.service;

import com.management.warehouse.model.Container;
import com.management.warehouse.model.Order;
import com.management.warehouse.repository.ContainerRepository;
import com.management.warehouse.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ContainerRepository containerRepository;

    @Override
    public Page<Order> getOrdersPaginated(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public List<Order> setDelivered(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order od = order.get();
            od.setDelivered(true);
            od.setDateDelivered(LocalDateTime.now());
            orderRepository.save(od);
            Container container = containerRepository.getById(od.getContainer().getId());
            if (od.getProcess().getProcessType().equalsIgnoreCase("loading")) {
                container.setAmount(container.getAmount() - od.getAmount());
            } else {
                container.setAmount(container.getAmount() + od.getAmount());
            }
            containerRepository.save(container);
        }
        return orderRepository.findAll();
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void save(int id) {
        Order order = orderRepository.findById(id);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(int id) {

        Order order = orderRepository.findById(id);
        Container container = containerRepository.getById(order.getContainer().getId());
        ;
        if (order.getProcess().getProcessType().equalsIgnoreCase("loading")) {
            container.setAmount(container.getAmount() - order.getAmount());
        } else {
            container.setAmount(container.getAmount() + order.getAmount());
        }
        containerRepository.save(container);
        orderRepository.deleteById(id);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.getById(id);
    }

    @Override
    public List<Order> showUpdatedForm() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
