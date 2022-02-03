package com.management.warehouse.model.order;

import com.management.warehouse.exception.ContainerAmountException;
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
        if (userRepository.findByEmail(orderDto.getUser().getEmail()).getRole() == UserRole.ROLE_USER) {
            orderDto.setType("loading");
        } else {
            orderDto.setType("unloading");
        }
        if(orderDto.getAmountOfOrderedContainers() > containerRepository.findByNameAllIgnoreCase(orderDto.getContainer().getName()).getContainersAmount()){
            throw new ContainerAmountException("Container amount is not enough to realize order: " + orderDto.getContainer().getName());
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
        Container container = containerRepository.getById(order.getContainer().getId());
        if (order.getType().equalsIgnoreCase("loading")) {
            container.setContainersAmount(container.getContainersAmount() - order.getAmountOfOrderedContainers());
        } else {
            container.setContainersAmount(container.getContainersAmount() + order.getAmountOfOrderedContainers());
        }
        containerRepository.save(container);
        return OrderConverter.convertToOrderDto(order);
    }

    public void setDelivered(UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent() && !order.get().isDelivered()) {
            Order od = order.get();
            od.setDelivered(true);
            od.setDateDelivered(LocalDateTime.now());
            orderRepository.save(od);
        }
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
        Container container = containerRepository.getById(orderToDelete.getContainer().getId());
        if(orderToDelete.getType().equalsIgnoreCase("loading")){
            container.setContainersAmount(container.getContainersAmount() + orderToDelete.getAmountOfOrderedContainers());
        } else {
            container.setContainersAmount(container.getContainersAmount() - orderToDelete.getAmountOfOrderedContainers());
        }
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
