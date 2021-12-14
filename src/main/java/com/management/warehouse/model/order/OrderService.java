package com.management.warehouse.model.order;

import com.management.warehouse.model.container.ContainerRepository;
import com.management.warehouse.model.truck.TruckRepository;
import com.management.warehouse.model.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ContainerRepository containerRepository;
    private final TruckRepository truckRepository;
    private final UserRepository userRepository;

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderConverter::convertToOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto addOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .id(UUID.randomUUID())
                .truck(truckRepository.findByRegNumberAllIgnoreCase(orderDto.getTruck().getRegNumber()))
                .container(containerRepository.findByNameAllIgnoreCase(orderDto.getContainer().getName()))
                .amountOfOrderedContainers(orderDto.getAmountOfOrderedContainers())
                .type(orderDto.getType())
                .user(userRepository.findByEmail(orderDto.getUser().getEmail()))
                .date(LocalDateTime.now())
                .delivered(false)
                .dateDelivered(null)
                .build();
        orderRepository.save(order);
        return OrderConverter.convertToOrderDto(order);
    }

//    public Page<Order> getOrdersPaginated(Pageable pageable) {
//        return orderRepository.findAll(pageable);
//    }
//
//    public List<Order> setDelivered(UUID id) {
//        Optional<Order> order = orderRepository.findById(id);
//        if (order.isPresent()) {
//            Order od = order.get();
//            od.setDelivered(true);
//            od.setDateDelivered(LocalDateTime.now());
//            orderRepository.save(od);
//            Container container = containerRepository.getById(od.getContainer().getId());
//            if (od.getType().equalsIgnoreCase("loading")) {
//                container.setAmount(container.getAmount() - od.getAmount());
//            } else {
//                container.setAmount(container.getAmount() + od.getAmount());
//            }
//            containerRepository.save(container);
//        }
//        return orderRepository.findAll();
//    }
//
//
//    public void saveOrder(Order order) {
//        orderRepository.save(order);
//    }
//
//    public void save(UUID id) {
//        Optional<Order> order = orderRepository.findById(id);
//        orderRepository.save(order);
//    }
//
//    public void deleteOrder(UUID id) {
//
//        Order order = orderRepository.getById(id);
//        Container container = containerRepository.getById(order.getContainer().getId());
//
//        if (order.getType().equalsIgnoreCase("loading")) {
//            container.setAmount(container.getAmount() - order.getAmount());
//        } else {
//            container.setAmount(container.getAmount() + order.getAmount());
//        }
//        containerRepository.save(container);
//        orderRepository.deleteById(id);
//    }
//
//    public Order findById(UUID id) {
//        return orderRepository.getById(id);
//    }
//
//
//    public void updateOrder(Order order) {
//        orderRepository.save(order);
//    }
//
//    public List<Order> findAllByType(String type) {
//        return orderRepository.findAllByType(type);
//    }


}
