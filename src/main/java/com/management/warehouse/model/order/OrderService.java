package com.management.warehouse.model.order;

import com.management.warehouse.exception.FieldDoesNotExistException;
import com.management.warehouse.exception.OrderNotFoundException;
import com.management.warehouse.exception.truck.TruckNotFoundException;
import com.management.warehouse.model.container.Container;
import com.management.warehouse.model.container.ContainerRepository;
import com.management.warehouse.model.truck.Truck;
import com.management.warehouse.model.truck.TruckConverter;
import com.management.warehouse.model.truck.TruckDto;
import com.management.warehouse.model.truck.TruckRepository;
import com.management.warehouse.model.user.UserRepository;
import com.management.warehouse.model.user.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        if (orderDto.getUser().getRole() == UserRole.ROLE_USER) {
            orderDto.setType("loading");
        } else {
            orderDto.setType("unloading");
        }
        Order order = Order.builder()
                .id(UUID.randomUUID())
                .truck(truckRepository.findByRegNumberAllIgnoreCase(orderDto.getTruck().getRegNumber()))
                .container(containerRepository.findByNameAllIgnoreCase(orderDto.getContainer().getName()))
                .amountOfOrderedContainers(orderDto.getAmountOfOrderedContainers())
                .type(orderDto.getType())
                .user(userRepository.findByEmail(orderDto.getUser().getEmail()))
                .type(orderDto.getType())
                .date(LocalDateTime.now())
                .delivered(false)
                .dateDelivered(null)
                .build();
        orderRepository.save(order);
        return OrderConverter.convertToOrderDto(order);
    }

    public Page<Order> getOrdersPaginated(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public List<Order> setDelivered(UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order od = order.get();
            od.setDelivered(true);
            od.setDateDelivered(LocalDateTime.now());
            orderRepository.save(od);
            Container container = containerRepository.getById(od.getContainer().getId());
            if (od.getType().equalsIgnoreCase("loading")) {
                container.setContainersAmount(container.getContainersAmount() - od.getAmountOfOrderedContainers());
            } else {
                container.setContainersAmount(container.getContainersAmount() + od.getAmountOfOrderedContainers());
            }
            containerRepository.save(container);
        }
        return orderRepository.findAll();
    }

    private Order findOrderInDatabase(UUID id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new OrderNotFoundException("Could not find truck with id: " + id));
    }

    public OrderDto findById(UUID id) {
        Order order = findOrderInDatabase(id);
        return OrderConverter.convertToOrderDto(order);
    }

    public OrderDto deleteOrder(UUID id){
        Order orderToDelete = findOrderInDatabase(id);
        orderRepository.deleteById(id);
        return OrderConverter.convertToOrderDto(orderToDelete);
    }

    public OrderDto updateOrder(UUID id, Map<Object, Object> fields) {
        Order order = findOrderInDatabase(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Order.class, (String) key);
            if (field == null) {
                throw new FieldDoesNotExistException("Field " + key + " does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, order, value);
        });
        orderRepository.save(order);
        return OrderConverter.convertToOrderDto(order);
    }
}
